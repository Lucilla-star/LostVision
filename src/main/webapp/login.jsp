<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lost Vision - Login 🛹</title>
    <link rel="stylesheet" type="text/css" href="stylesheets/login.css">
</head>
<body>

    <div class="login-container">
        <h2>Lost Vision 🛹</h2>
        <p class="subtitle">Accedi al tuo account Streetwear</p>

        <%-- Se la Servlet ci rimanda un messaggio d'errore, lo stampiamo qui --%>
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

        <%-- Form che invia i dati alla LoginServlet tramite metodo POST --%>
        <form action="<%= request.getContextPath() %>/login" method="post">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="esempio@email.com" required>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="••••••••" required>
            </div>

            <button type="submit" class="btn-login">Accedi</button>
        </form>

        <div class="footer-link">
            Non hai un account? <a href="<%= request.getContextPath() %>/registrazione">Registrati ora</a>
        </div>
    </div>

</body>
</html>