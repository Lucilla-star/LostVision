package it.lostvision.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.lostvision.model.prodotto.ProdottoBean;
import it.lostvision.model.prodotto.ProdottoDAO;
import it.lostvision.model.prodotto.ProdottoDAOImpl;

@WebServlet("/prodotto")
public class DettaglioProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParam = request.getParameter("id");
		if (idParam != null) {
			try {
				int idProdotto = Integer.parseInt(idParam);
				ProdottoDAO dao = new ProdottoDAOImpl();
				
				ProdottoBean prodotto = dao.doRetrieveAll("").stream()
						.filter(p -> p.getIdProdotto() == idProdotto)
						.findFirst().orElse(null);
				
				request.setAttribute("prodotto", prodotto);
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher("/WEB-INF/dettaglio.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}