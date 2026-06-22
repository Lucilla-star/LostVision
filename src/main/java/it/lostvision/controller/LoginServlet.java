package it.lostvision.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.lostvision.model.utente.UtenteBean;
import it.lostvision.model.utente.UtenteDAO;
import it.lostvision.model.utente.UtenteDAOImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UtenteDAO dao = new UtenteDAOImpl();
		try {
			UtenteBean utente = dao.doRetrieveByEmail(email);
			if (utente != null && utente.getPassword().equals(password)) {
				HttpSession session = request.getSession();
				session.setAttribute("utente", utente);
				response.sendRedirect(request.getContextPath() + "/catalogo");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("errore", "Credenziali errate!");
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}
}