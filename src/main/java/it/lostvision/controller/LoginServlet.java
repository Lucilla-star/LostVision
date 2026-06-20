package it.lostvision.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.lostvision.model.Utente;
import it.lostvision.model.UtenteDAO;
import it.lostvision.model.UtenteDAOImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private UtenteDAO utenteDAO = new UtenteDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Se l'utente richiede la pagina di login tramite un link (GET), lo mandiamo semplicemente alla pagina jsp
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Quando l'utente preme il tasto "Accedi" nel form (POST), leggiamo i dati inviati
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        try {
            // Chiediamo al database se esiste un utente con questa email e password
            Utente utente = utenteDAO.doRetrieveByLogin(email, password);
            
            if (utente != null) {
                // LOGIN RIUSCITO: creiamo una sessione e salviamo l'oggetto utente dentro
                HttpSession session = request.getSession();
                session.setAttribute("utente", utente);
                
                // Lo reindirizziamo al catalogo del sito
                response.sendRedirect(request.getContextPath() + "/catalogo");
            } else {
                // LOGIN FALLITO: rimandiamo l'utente alla pagina di login con un messaggio d'errore
                request.setAttribute("errore", "Email o password errate. Riprova!");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante il login sul database.");
        }
    }
}