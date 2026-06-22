package it.lostvision.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.sql.Date;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.lostvision.model.carrello.CarrelloBean;
import it.lostvision.model.ordine.OrdineBean;
import it.lostvision.model.ordine.OrdineDAO;
import it.lostvision.model.ordine.OrdineDAOImpl;
import it.lostvision.model.utente.UtenteBean;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		CarrelloBean carrello = (CarrelloBean) session.getAttribute("carrello");

		if (utente == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		if (carrello == null || carrello.getArticoli().isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/carrello");
			return;
		}

		OrdineDAO ordineDAO = new OrdineDAOImpl();
		OrdineBean nuovoOrdine = new OrdineBean();
		nuovoOrdine.setDataOrdine(new Date(System.currentTimeMillis()));
		nuovoOrdine.setTotale(carrello.getTotaleComplessivo());
		nuovoOrdine.setEmailUtente(utente.getEmail());

		try {
			ordineDAO.doSave(nuovoOrdine);
			carrello.svuota();
			request.setAttribute("messaggio", "Ordine completato con successo! 🛹");
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("messaggio", "Errore durante il checkout.");
		}

		request.getRequestDispatcher("/WEB-INF/carrello.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}