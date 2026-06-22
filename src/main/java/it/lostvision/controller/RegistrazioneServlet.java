package it.lostvision.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.lostvision.model.utente.UtenteBean;
import it.lostvision.model.utente.UtenteDAO;
import it.lostvision.model.utente.UtenteDAOImpl;

@WebServlet("/registrazione")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/registrazione.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String password = request.getParameter("password");

		UtenteBean nuovoUtente = new UtenteBean();
		nuovoUtente.setEmail(email);
		nuovoUtente.setNome(nome);
		nuovoUtente.setCognome(cognome);
		nuovoUtente.setPassword(password);

		UtenteDAO dao = new UtenteDAOImpl();
		try {
			dao.doSave(nuovoUtente);
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("errore", "Registrazione fallita!");
		request.getRequestDispatcher("/WEB-INF/registrazione.jsp").forward(request, response);
	}
}