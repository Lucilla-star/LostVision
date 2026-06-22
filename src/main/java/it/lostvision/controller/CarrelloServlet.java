package it.lostvision.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.lostvision.model.carrello.CarrelloBean;
import it.lostvision.model.prodotto.ProdottoBean;
import it.lostvision.model.prodotto.ProdottoDAO;
import it.lostvision.model.prodotto.ProdottoDAOImpl;

@WebServlet("/carrello")
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// Recupera o crea il carrello dalla sessione usando la nuova classe CarrelloBean
		CarrelloBean carrello = (CarrelloBean) session.getAttribute("carrello");
		if (carrello == null) {
			carrello = new CarrelloBean();
			session.setAttribute("carrello", carrello);
		}

		String azione = request.getParameter("azione");
		String idParam = request.getParameter("idProdotto");

		if (azione != null && idParam != null) {
			try {
				int idProdotto = Integer.parseInt(idParam);
				ProdottoDAO prodDAO = new ProdottoDAOImpl();

				if (azione.equals("aggiungi")) {
					// Cerca il prodotto nel database per aggiungerlo al carrello temporaneo
					ProdottoBean p = prodDAO.doRetrieveAll("").stream()
							.filter(prod -> prod.getIdProdotto() == idProdotto)
							.findFirst().orElse(null);
					
					if (p != null) {
						carrello.aggiungiArticolo(p, 1);
					}
				} else if (azione.equals("rimuovi")) {
					carrello.rimuoviArticolo(idProdotto);
				}
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}

		// Invia i dati alla pagina JSP del carrello
		request.getRequestDispatcher("/WEB-INF/carrello.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}