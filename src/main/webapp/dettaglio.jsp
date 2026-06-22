<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="it.lostvision.model.prodotto.ProdottoBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dettaglio Prodotto</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .btn { background-color: #007bff; color: white; padding: 5px 10px; text-decoration: none; border-radius: 3px; }
    </style>
</head>
<body>
    <%
        ProdottoBean p = (ProdottoBean) request.getAttribute("prodotto");
        if (p != null) {
    %>
            <h1><%= p.getNomeProdotto() %></h1>
            <p><strong>Tipologia:</strong> <%= p.getTipologia() %></p>
            <p><strong>Descrizione:</strong> <%= p.getDescrizione() %></p>
            <p><strong>Prezzo:</strong> <%= p.getPrezzo() %> €</p>
            <p><strong>Disponibilità:</strong> <%= p.getDisponibilita() %> pezzi rimasti</p>
            
            <a href="carrello?azione=aggiungi&idProdotto=<%= p.getIdProdotto() %>" class="btn" style="background-color: #28a745;">Aggiungi al Carrello</a>
    <%
        } else {
    %>
            <p>Prodotto non trovato o non specificato.</p>
    <%
        }
    %>
    <br><br>
    <a href="catalogo" class="btn">Torna al Catalogo</a>
</body>
</html>