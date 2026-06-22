<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection" %>
<%@ page import="it.lostvision.model.prodotto.ProdottoBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Catalogo LostVision</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }
        .prodotto-card { background: white; padding: 15px; margin-bottom: 10px; border-radius: 5px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        .btn { background-color: #007bff; color: white; padding: 5px 10px; text-decoration: none; border-radius: 3px; }
        .btn-carrello { background-color: #28a745; }
    </style>
</head>
<body>
    <h1>Catalogo Prodotti 🛹</h1>
    <a href="carrello" class="btn">Vai al Carrello</a>
    <hr>
    
    <%
        Collection<ProdottoBean> prodotti = (Collection<ProdottoBean>) request.getAttribute("prodotti");
        if (prodotti != null && !prodotti.isEmpty()) {
            for (ProdottoBean p : prodotti) {
    %>
                <div class="prodotto-card">
                    <h3><%= p.getNomeProdotto() %></h3>
                    <p><%= p.getDescrizione() %></p>
                    <p><strong>Prezzo:</strong> <%= p.getPrezzo() %> €</p>
                    <a href="prodotto?id=<%= p.getIdProdotto() %>" class="btn">Vedi Dettaglio</a>
                    <a href="carrello?azione=aggiungi&idProdotto=<%= p.getIdProdotto() %>" class="btn btn-carrello">Aggiungi al Carrello</a>
                </div>
    <%
            }
        } else {
    %>
            <p>Nessun prodotto disponibile nel catalogo.</p>
    <%
        }
    %>
</body>
</html>