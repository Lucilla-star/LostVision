<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, it.lostvision.model.Prodotto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lost Vision - Streetwear Catalogo</title>
<style>
    body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 20px; }
    h1 { color: #333; text-align: center; text-transform: uppercase; }
    .grid-container { display: grid; grid-template-columns: repeat(auto-fill, minmax(250px, 1fr)); gap: 20px; margin-top: 30px; }
    .card { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); text-align: center; }
    .card img { max-width: 100%; height: 200px; object-fit: cover; border-radius: 4px; }
    .card h3 { margin: 10px 0; color: #111; }
    .card .prezzo { font-weight: bold; color: #ff5722; font-size: 1.2em; }
    .card .taglia { color: #777; font-size: 0.9em; }
</style>
</head>
<body>

    <h1>Catalogo Lost Vision 🛹</h1>
    
    <div class="grid-container">
    <%
        // Recuperiamo la lista dei prodotti che la Servlet ci ha messo a disposizione
        Collection<Prodotto> prodotti = (Collection<Prodotto>) request.getAttribute("prodotti");
        if (prodotti != null && !prodotti.isEmpty()) {
            for (Prodotto p : prodotti) {
    %>
                <div class="card">
                    <img src="<%= (p.getImmagine() != null && !p.getImmagine().equals("")) ? p.getImmagine() : "https://via.placeholder.com/200" %>" alt="<%= p.getNomeProdotto() %>">
                    <h3><%= p.getNomeProdotto() %></h3>
                    <p><%= p.getDescrizione() %></p>
                    <p class="taglia">Taglia: <%= p.getTaglia() %></p>
                    <p class="prezzo"><%= String.format("%.2f", p.getPrezzo()) %> &euro;</p>
<div style="margin-top: 15px;">
    <a href="<%= request.getContextPath() %>/prodotto?id=<%= p.getIdProdotto() %>" 
       style="display: inline-block; background-color: #1a1a1a; color: white; padding: 10px 15px; text-decoration: none; font-weight: bold; font-size: 14px; border-radius: 6px; text-transform: uppercase; transition: background 0.2s;">
       Vedi Dettaglio 👀
    </a>
</div>
 </div>
    <%
            }
        } else {
    %>
            <p style="text-align: center; grid-column: 1/-1;">Nessun prodotto trovato nel database. Assicurati di aver inserito qualche riga nella tabella 'prodotto'!</p>
    <%
        }
    %>
    </div>

</body>
</html>