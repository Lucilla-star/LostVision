<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="it.lostvision.model.Carrello" %>
<%@ page import="it.lostvision.model.ArticoloCarrello" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lost Vision - Il Tuo Carrello 🛒</title>
    <style>
        * {
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
        }

        body {
            background-color: #f4f4f6;
            color: #1a1a1a;
            padding: 40px 20px;
        }

        .cart-container {
            max-width: 900px;
            margin: 0 auto;
            background: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
        }

        h2 {
            font-size: 26px;
            font-weight: 700;
            text-transform: uppercase;
            letter-spacing: 1px;
            margin-bottom: 25px;
            border-bottom: 2px solid #1a1a1a;
            padding-bottom: 10px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 30px;
        }

        th {
            text-align: left;
            padding: 12px;
            background-color: #1a1a1a;
            color: #ffffff;
            font-size: 13px;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        th:first-child { border-top-left-radius: 6px; border-bottom-left-radius: 6px; }
        th:last-child { border-top-right-radius: 6px; border-bottom-right-radius: 6px; }

        td {
            padding: 15px 12px;
            border-bottom: 1px solid #e5e7eb;
            font-size: 15px;
        }

        .product-name {
            font-weight: 600;
            color: #1a1a1a;
        }

        .btn-remove {
            background-color: #ef4444;
            color: #ffffff;
            border: none;
            padding: 6px 12px;
            font-size: 12px;
            font-weight: 600;
            border-radius: 6px;
            cursor: pointer;
            text-decoration: none;
            transition: background 0.2s;
        }

        .btn-remove:hover {
            background-color: #dc2626;
        }

        .cart-summary {
            text-align: right;
            padding-top: 20px;
            border-top: 2px dashed #e5e7eb;
        }

        .total-price {
            font-size: 22px;
            font-weight: 700;
            margin-bottom: 20px;
        }

        .btn-checkout {
            background-color: #1a1a1a;
            color: #ffffff;
            padding: 14px 30px;
            font-size: 16px;
            font-weight: 600;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            transition: background 0.2s;
        }

        .btn-checkout:hover {
            background-color: #333333;
        }

        .back-link {
            color: #666;
            text-decoration: none;
            font-size: 14px;
            display: inline-block;
            margin-bottom: 20px;
            font-weight: 500;
        }

        .back-link:hover {
            color: #1a1a1a;
            text-decoration: underline;
        }

        .empty-msg {
            text-align: center;
            padding: 40px 0;
            color: #666;
            font-size: 16px;
        }
    </style>
</head>
<body>

    <div class="cart-container">
        <a href="<%= request.getContextPath() %>/catalogo" class="back-link">← Torna al Catalogo</a>
        
        <h2>IL TUO CARRELLO 🛹</h2>

        <%
            Carrello carrello = (Carrello) session.getAttribute("carrello");
            if (carrello == null || carrello.getArticoli().isEmpty()) {
        %>
            <div class="empty-msg">
                <p>Il tuo carrello è attualmente vuoto. Corri a svaligiare il catalogo!</p>
            </div>
        <%
            } else {
        %>
            <table>
                <thead>
                    <tr>
                        <th>Prodotto</th>
                        <th>Prezzo Unitario</th>
                        <th>Quantità</th>
                        <th>Totale parziale</th>
                        <th>Azione</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (ArticoloCarrello art : carrello.getArticoli()) {
                    %>
                        <tr>
                            <td class="product-name"><%= art.getProdotto().getNomeProdotto() %></td>
                            <td><%= String.format("%.2f €", art.getProdotto().getPrezzo()) %></td>
                            <td><strong><%= art.getQuantita() %></strong></td>
                            <td><%= String.format("%.2f €", art.getPrezzoTotale()) %></td>
                            <td>
                                <a href="<%= request.getContextPath() %>/carrello?azione=rimuovi&idProdotto=<%= art.getProdotto().getIdProdotto() %>" class="btn-remove">Rimuovi</a>
                            </td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>

            <div class="cart-summary">
                <div class="total-price">
                    Totale Complessivo: <span><%= String.format("%.2f €", carrello.getTotaleComplessivo()) %></span>
                </div>
               <a href="<%= request.getContextPath() %>/checkout" class="btn-checkout">Procedi al Checkout →</a>
            </div>
        <%
            }
        %>
    </div>

</body>
</html>