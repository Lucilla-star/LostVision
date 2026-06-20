package it.lostvision.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.lostvision.model.Prodotto;
import it.lostvision.model.ProdottoDAO;
import it.lostvision.model.ProdottoDAOImpl;

@WebServlet("/prodotto")
public class DettaglioProdottoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProdottoDAO prodottoDAO = new ProdottoDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProdotto = request.getParameter("id");
        
        if (idProdotto != null) {
            try {
                // Recuperiamo il singolo prodotto dal DB
                Prodotto prodotto = prodottoDAO.doRetrieveByKey(idProdotto);
                if (prodotto != null) {
                    // Lo passiamo come attributo alla richiesta
                    request.setAttribute("prodottoDettaglio", prodotto);
                    request.getRequestDispatcher("/dettaglio.jsp").forward(request, response);
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // Se qualcosa va storto, torniamo al catalogo
        response.sendRedirect(request.getContextPath() + "/catalogo");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}