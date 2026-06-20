package it.lostvision.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.lostvision.model.Utente;
import it.lostvision.model.UtenteDAO;
import it.lostvision.model.UtenteDAOImpl;

@WebServlet("/registrazione")
public class RegistrazioneServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private UtenteDAO utenteDAO = new UtenteDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Se l'utente clicca sul link "Registrati", lo mandiamo alla pagina jsp del form
        request.getRequestDispatcher("/registrazione.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Impostiamo la codifica per non avere problemi con accenti o caratteri strani
        request.setCharacterEncoding("UTF-8");
        
        // Recuperiamo tutti i campi inviati dal form HTML
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String via = request.getParameter("via");
        String numeroCivicoStr = request.getParameter("numeroCivico");
        String cap = request.getParameter("cap");
        String citta = request.getParameter("citta");
        String provincia = request.getParameter("provincia");
        
     // Genera un id corto di 4 caratteri totali (Es. U + numero tra 100 e 999)
        String idUtente = "U" + (int)(100 + Math.random() * 900);
        
        // Convertiamo il numero civico in int (se vuoto mettiamo 0)
        int numeroCivico = 0;
        if (numeroCivicoStr != null && !numeroCivicoStr.isEmpty()) {
            numeroCivico = Integer.parseInt(numeroCivicoStr);
        }

        // Creiamo l'oggetto Utente con i dati raccolti
        Utente nuovoUtente = new Utente();
        nuovoUtente.setIdUtente(idUtente);
        nuovoUtente.setNomeUtente(nome);
        nuovoUtente.setEmail(email);
        nuovoUtente.setPassword(password);
        nuovoUtente.setVia(via);
        nuovoUtente.setNumeroCivico(numeroCivico);
        nuovoUtente.setCap(cap);
        nuovoUtente.setCitta(citta);
        nuovoUtente.setProvincia(provincia);
        nuovoUtente.setAdmin(false); // Di base i nuovi registrati NON sono admin

        try {
            // Salviamo l'utente sul database usando il DAO
            utenteDAO.doSave(nuovoUtente);
            
            // Registrazione completata! Lo mandiamo alla pagina di login con un messaggio di successo
            request.setAttribute("successo", "Registrazione avvenuta con successo! Ora puoi accedere.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            
        } catch (SQLException e) {
            e.printStackTrace();
            // Se c'è un errore (ad esempio l'email esiste già), ricarichiamo la pagina con l'errore
            request.setAttribute("errore", "Errore durante la registrazione. L'email potrebbe essere già registrata.");
            request.getRequestDispatcher("/registrazione.jsp").forward(request, response);
        }
    }
}