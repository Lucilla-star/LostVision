<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="it.lostvision.model.Prodotto" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lost Vision - Dettaglio Prodotto</title>
    <style>
        * { box-sizing: border-box; font-family: 'Segoe UI', sans-serif; margin: 0; padding: 0; }
        body { background-color: #f4f4f6; color: #1a1a1a; padding: 40px 20px; }
        .container { max-width: 1000px; margin: 0 auto; background: #fff; padding: 40px; border-radius: 12px; box-shadow: 0 4px 15px rgba(0,0,0,0.05); display: flex; gap: 40px; }
        .product-image { flex: 1; max-width: 450px; }
        .product-image img { width: 100%; height: auto; border-radius: 8px; object-fit: cover; }
        .product-info { flex: 1; display: flex; flex-direction: column; justify-content: center; }
        h1 { font-size: 32px; text-transform: uppercase; margin-bottom: 15px; letter-spacing: 1px; }
        .price { font-size: 24px; font-weight: bold; color: #ff5722; margin-bottom: 20px; }
        .desc { color: #555; line-height: 1.6; margin-bottom: 25px; }
        .selector-group { margin-bottom: 20px; }
        label { display: block; font-weight: 600; text-transform: uppercase; font-size: 13px; margin-bottom: 8px; color: #333; }
        select, input[type="number"] { width: 100%; max-width: 200px; padding: 10px; border: 1px solid #ccc; border-radius: 6px; font-size: 15px; outline: none; }
        select:focus, input[type="number"]:focus { border-color: #1a1a1a; }
        .btn-add { background-color: #1a1a1a; color: white; border: none; padding: 15px 30px; font-size: 16px; font-weight: bold; text-transform: uppercase; border-radius: 8px; cursor: pointer; margin-top: 10px; transition: background 0.2s; width: 100%; max-width: 300px; }
        .btn-add:hover { background-color: #333; }
        .back-link { display: inline-block; margin-bottom: 20px; color: #666; text-decoration: none; font-weight: 500; }
        .back-link:hover { color: #1a1a1a; text-decoration: underline; }
    </style>
</head>
<body>

<%
    Prodotto p = (Prodotto) request.getAttribute("prodottoDettaglio");
    if (p != null) {
%>
    <div style="max-width: 1000px; margin: 0 auto;">
        <a href="<%= request.getContextPath() %>/catalogo" class="back-link">← Torna al Catalogo</a>
        
        <div class="container">
            <div class="product-image">
                <img src="<%= (p.getImmagine() != null && !p.getImmagine().equals("")) ? p.getImmagine() : "https://via.placeholder.com/450" %>" alt="<%= p.getNomeProdotto() %>">
            </div>
            
            <div class="product-info">
                <h1><%= p.getNomeProdotto() %></h1>
                <div class="price"><%= String.format("%.2f", p.getPrezzo()) %> €</div>
                <p class="desc"><%= p.getDescrizione() %></p>
                
                <form action="<%= request.getContextPath() %>/carrello" method="GET">
                    <input type="hidden" name="azione" value="aggiungi">
                    <input type="hidden" name="idProdotto" value="<%= p.getIdProdotto() %>">
                    
                    <div class="selector-group">
                        <label for="taglia">Seleziona Taglia:</label>
                        <select name="taglia" id="taglia">
                            <option value="S">S</option>
                            <option value="M" <%= "M".equals(p.getTaglia()) ? "selected" : "" %>>M</option>
                            <option value="L" <%= "L".equals(p.getTaglia()) ? "selected" : "" %>>L</option>
                            <option value="XL">XL</option>
                        </select>
                    </div>
                    
                    <div class="selector-group">
                        <label for="quantita">Quantità:</label>
                        <input type="number" id="quantita" name="quantita" value="1" min="1" max="10">
                    </div>
                    
                    <button type="submit" class="btn-add">Aggiungi al Carrello 🛒</button>
                </form>
            </div>
        </div>
    </div>
<%
    } else {
%>
    <p style="text-align: center;">Prodotto non trovato.</p>
<%
    }
%>

</body>
</html>