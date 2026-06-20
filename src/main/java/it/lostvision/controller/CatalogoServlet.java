package it.lostvision.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.lostvision.model.Prodotto;
import it.lostvision.model.ProdottoDAO;
import it.lostvision.model.ProdottoDAOImpl;

// Questa annotazione dice a Tomcat che quando visitiamo l'URL /catalogo deve rispondere questa Servlet
@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ProdottoDAO prodottoDAO = new ProdottoDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Prendiamo tutti i prodotti usando il DAO che hai creato prima
            Collection<Prodotto> prodotti = prodottoDAO.doRetrieveAll(null);
            
            // Salviamo la lista dei prodotti nella "request" così la pagina JSP potrà leggerla
            request.setAttribute("prodotti", prodotti);
            
        } catch (SQLException e) {
            System.out.println("Errore nel recupero dei prodotti nella Servlet: " + e.getMessage());
        }

        // Spediamo il controllo alla pagina JSP che si occuperà della grafica
        RequestDispatcher dispatcher = request.getRequestDispatcher("/catalogo.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}