package it.lostvision.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class ConnessioneDB {
    // IMPORTANTE: Se la tua porta di MySQL non è 3306, cambia il numero qui sotto.
    private static final String URL = "jdbc:mysql://localhost:3306/lostvision?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // Il tuo nome utente di MySQL (di solito è root)
    private static final String PASSWORD = "MyWorkBenchDiMarinoLucilla-2004"; // ATTENZIONE: Metti qui la password del TUO MySQL!
    
    // Questo è il pool (una lista di connessioni pronte all'uso)
    private static LinkedList<Connection> pool = new LinkedList<>();

    static {
        try {
            // Carichiamo il driver che abbiamo inserito prima nella cartella lib
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL non trovato: " + e.getMessage());
        }
    }

    // Questo metodo serve per "chiedere" una connessione quando dobbiamo fare una query
    public static synchronized Connection getConnection() throws SQLException {
        if (pool.isEmpty()) {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } else {
            Connection connection = pool.removeFirst();
            if (connection.isClosed()) {
                return getConnection(); // Se era chiusa, ne chiediamo un'altra
            }
            return connection;
        }
    }

    // Questo metodo serve per "restituire" la connessione al pool quando abbiamo finito
    public static synchronized void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    pool.addLast(connection);
                }
            } catch (SQLException e) {
                System.err.println("Errore nel rilascio della connessione: " + e.getMessage());
            }
        }
    }
}
