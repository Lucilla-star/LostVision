package it.lostvision.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.lostvision.model.Carrello;
import it.lostvision.model.Ordine;
import it.lostvision.model.OrdineDAO;
import it.lostvision.model.OrdineDAOImpl;
import it.lostvision.model.Utente; // Assicurati che si chiami così la classe Utente

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrdineDAO ordineDAO = new OrdineDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
     // 1. Recuperiamo l'oggetto Utente dalla sessione usando la chiave giusta
        it.lostvision.model.Utente utenteConnesso = (it.lostvision.model.Utente) session.getAttribute("utente");
        
        if (utenteConnesso == null) {
            // Se non è loggato, lo mandiamo al login
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // Estraiamo l'ID dall'oggetto utente
        String idUtente = utenteConnesso.getIdUtente();

        // 2. Recuperiamo il carrello
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        if (carrello == null || carrello.getArticoli().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/catalogo");
            return;
        }

        try {
            // 3. Prepariamo l'oggetto Ordine
            Ordine ordine = new Ordine();
            ordine.setDataOrdine(new Date(System.currentTimeMillis()));
            ordine.setTotale(carrello.getTotaleComplessivo());
            ordine.setStato("in elaborazione");
            ordine.setIdUtente(idUtente);
            ordine.setDettagli(carrello.getArticoli());

            // 4. Salviamo l'ordine su MySQL
            ordineDAO.doSave(ordine);

            // 5. Svuotiamo il carrello a acquisto completato!
            session.setAttribute("carrello", new Carrello());

            // Mandiamo l'utente a una pagina di successo (o al catalogo con un messaggio)
            request.setAttribute("messaggioSuccesso", "Ordine completato con successo! Grazie per aver acquistato su Lost Vision. 🛹");
            request.getRequestDispatcher("/catalogo").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/carrello.jsp?errore=1");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}