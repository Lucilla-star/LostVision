package it.lostvision.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.lostvision.model.prodotto.ProdottoBean;
import it.lostvision.model.prodotto.ProdottoDAO;
import it.lostvision.model.prodotto.ProdottoDAOImpl;

@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdottoDAO dao = new ProdottoDAOImpl();
		
		try {
			// Cambiato in ProdottoBean per seguire la nuova struttura ordinata
			Collection<ProdottoBean> catalogo = dao.doRetrieveAll("");
			request.setAttribute("prodotti", catalogo);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/catalogo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}