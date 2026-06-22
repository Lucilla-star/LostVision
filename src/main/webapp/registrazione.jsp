<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lost Vision - Registrazione 🛹</title>
  <link rel="stylesheet" type="text/css" href="stylesheets/registrazione.css">
</head>
<body>

    <div class="reg-container">
        <h2>Lost Vision 🛹</h2>
        <p class="subtitle">Crea il tuo account ed entra nel club</p>

        <% 
            String errore = (String) request.getAttribute("errore");
            if (errore != null) {
        %>
            <div class="alert-error">
                <%= errore %>
            </div>
        <% 
            } 
        %>

        <form action="<%= request.getContextPath() %>/registrazione" method="post">
            
            <div class="form-group">
                <label for="nome">Nome Completo</label>
                <input type="text" id="nome" name="nome" placeholder="Mario Rossi" required>
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="mario@esempio.com" required>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Scegli una password" required>
            </div>

            <hr style="border: 0; border-top: 1px solid #eee; margin: 20px 0;">

            <div class="form-row">
                <div class="form-group" style="flex: 3;">
                    <label for="via">Via / Piazza</label>
                    <input type="text" id="via" name="via" placeholder="Via Roma" required>
                </div>
                <div class="form-group" style="flex: 1;">
                    <label for="numeroCivico">Civico</label>
                    <input type="number" id="numeroCivico" name="numeroCivico" placeholder="10" required>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group" style="flex: 2;">
                    <label for="citta">Città</label>
                    <input type="text" id="citta" name="citta" placeholder="Nocera" required>
                </div>
                <div class="form-group" style="flex: 1;">
                    <label for="provincia">Prov.</label>
                    <input type="text" id="provincia" name="provincia" placeholder="SA" maxlength="2" required>
                </div>
                <div class="form-group" style="flex: 1.5;">
                    <label for="cap">CAP</label>
                    <input type="text" id="cap" name="cap" placeholder="84015" maxlength="5" required>
                </div>
            </div>

            <button type="submit" class="btn-reg">Registrati</button>
        </form>

        <div class="footer-link">
            Hai già un account? <a href="<%= request.getContextPath() %>/login">Accedi</a>
        </div>
    </div>

</body>
</html>