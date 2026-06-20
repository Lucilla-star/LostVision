<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lost Vision - Registrazione 🛹</title>
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
            min-height: 100vh;
            padding: 20px 0;
        }

        .reg-container {
            background-color: #ffffff;
            padding: 35px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
            width: 100%;
            max-width: 500px;
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
            margin-bottom: 16px;
            text-align: left;
        }

        .form-row {
            display: flex;
            gap: 12px;
        }

        .form-row .form-group {
            flex: 1;
        }

        label {
            display: block;
            font-size: 12px;
            font-weight: 600;
            color: #333;
            margin-bottom: 6px;
            text-transform: uppercase;
        }

        input {
            width: 100%;
            padding: 10px 12px;
            border: 1px solid #d1d5db;
            border-radius: 8px;
            font-size: 14px;
            background-color: #f9fafb;
            transition: all 0.2s ease;
        }

        input:focus {
            outline: none;
            border-color: #1a1a1a;
            background-color: #ffffff;
            box-shadow: 0 0 0 3px rgba(26, 26, 26, 0.1);
        }

        .btn-reg {
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
            margin-top: 15px;
        }

        .btn-reg:hover {
            background-color: #333333;
        }

        .footer-link {
            margin-top: 20px;
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