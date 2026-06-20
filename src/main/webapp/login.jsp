<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lost Vision - Login 🛹</title>
    <style>
        * {
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
        }

        body {
            background-color: #f4f4f6;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-container {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
            width: 100%;
            max-width: 400px;
            text-align: center;
        }

        h2 {
            font-size: 24px;
            font-weight: 700;
            color: #1a1a1a;
            margin-bottom: 8px;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        p.subtitle {
            color: #666;
            font-size: 14px;
            margin-bottom: 24px;
        }

        .alert-error {
            background-color: #fde8e8;
            color: #e02424;
            padding: 12px;
            border-radius: 8px;
            font-size: 14px;
            margin-bottom: 20px;
            text-align: left;
            border-left: 4px solid #e02424;
        }

        .form-group {
            margin-bottom: 20px;
            text-align: left;
        }

        label {
            display: block;
            font-size: 13px;
            font-weight: 600;
            color: #333;
            margin-bottom: 6px;
            text-transform: uppercase;
        }

        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 12px 14px;
            border: 1px solid #d1d5db;
            border-radius: 8px;
            font-size: 15px;
            background-color: #f9fafb;
            transition: all 0.2s ease;
        }

        input[type="email"]:focus,
        input[type="password"]:focus {
            outline: none;
            border-color: #1a1a1a;
            background-color: #ffffff;
            box-shadow: 0 0 0 3px rgba(26, 26, 26, 0.1);
        }

        .btn-login {
            background-color: #1a1a1a;
            color: #ffffff;
            border: none;
            width: 100%;
            padding: 14px;
            font-size: 16px;
            font-weight: 600;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.2s ease;
            margin-top: 10px;
        }

        .btn-login:hover {
            background-color: #333333;
        }

        .footer-link {
            margin-top: 24px;
            font-size: 14px;
            color: #666;
        }

        .footer-link a {
            color: #1a1a1a;
            text-decoration: none;
            font-weight: 600;
        }

        .footer-link a:hover {
            text-decoration: underline;
        }
    </style>
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