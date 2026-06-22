<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="it.lostvision.model.carrello.CarrelloBean" %>
<%@ page import="it.lostvision.model.carrello.ArticoloCarrelloBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Il tuo Carrello</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #f2f2f2; }
        .btn { background-color: #007bff; color: white; padding: 5px 10px; text-decoration: none; border-radius: 3px; }
        .btn-rimuovi { background-color: #dc3545; }
    </style>
</head>
<body>
    <h1>Il tuo Carrello 🛒</h1>
    
    <%
        String msg = (String) request.getAttribute("messaggio");
        if (msg != null) {
    %>
            <p style="color: green; font-weight: bold;"><%= msg %></p>
    <%
        }
    %>

    <%
        CarrelloBean carrello = (CarrelloBean) session.getAttribute("carrello");
        if (carrello != null && !carrello.getArticoli().isEmpty()) {
    %>
            <table>
                <thead>
                    <tr>
                        <th>Prodotto</th>
                        <th>Prezzo Unitario</th>
                        <th>Quantità</th>
                        <th>Totale parziale</th>
                        <th>Azioni</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (ArticoloCarrelloBean art : carrello.getArticoli()) {
                    %>
                            <tr>
                                <td><%= art.getProdotto().getNomeProdotto() %></td>
                                <td><%= art.getProdotto().getPrezzo() %> €</td>
                                <td><%= art.getQuantita() %></td>
                                <td><%= art.getPrezzoTotale() %> €</td>
                                <td>
                                    <a href="carrello?azione=rimuovi&idProdotto=<%= art.getProdotto().getIdProdotto() %>" class="btn btn-rimuovi">Rimuovi</a>
                                </td>
                            </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            
            <h3>Totale Complessivo: <%= carrello.getTotaleComplessivo() %> €</h3>
            <br>
            <a href="checkout" class="btn" style="background-color: #ffc107; color: black; font-weight: bold;">Procedi al Checkout (Acquista)</a>
    <%
        } else {
    %>
            <p>Il tuo carrello è attualmente vuoto.</p>
    <%
        }
    %>
    
    <br><br>
    <a href="catalogo" class="btn">Torna al Catalogo</a>
</body>
</html>