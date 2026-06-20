package it.lostvision.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.lostvision.model.Carrello;
import it.lostvision.model.Prodotto;
import it.lostvision.model.ProdottoDAO;
import it.lostvision.model.ProdottoDAOImpl;

@WebServlet("/carrello")
public class CarrelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ProdottoDAO prodottoDao = new ProdottoDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        
        if (carrello == null) {
            carrello = new Carrello();
            session.setAttribute("carrello", carrello);
        }
        
        String azione = request.getParameter("azione");
        
        if (azione != null) {
            String idProdotto = request.getParameter("idProdotto");
            
            if (azione.equals("aggiungi")) {
                try {
                    Prodotto p = prodottoDao.doRetrieveByKey(idProdotto);
                    if (p != null) {
                        int qta = 1;
                        String qtaParam = request.getParameter("quantita");
                        if (qtaParam != null) {
                            qta = Integer.parseInt(qtaParam);
                        }
                        
                        String tagliaScelta = request.getParameter("taglia");
                        if (tagliaScelta != null) {
                            p.setTaglia(tagliaScelta);
                        }

                        carrello.aggiungiProdotto(p, qta);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (azione.equals("rimuovi")) {
                carrello.rimuoviProdotto(idProdotto);
            }
        }
    
        request.getRequestDispatcher("/carrello.jsp").forward(request, response);
    } 

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}