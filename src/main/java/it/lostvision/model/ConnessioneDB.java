package it.lostvision.model;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnessioneDB {

    private static DataSource ds;

    static {
        try {
            // Questo blocco cerca la configurazione "jdbc/lostvision" che abbiamo scritto nel context.xml
            InitialContext ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/lostvision");
        } catch (NamingException e) {
            System.out.println("Errore nella configurazione del DataSource: " + e.getMessage());
        }
    }

    // Ogni volta che nei DAO scriveremo ConnessioneDB.getConnection(), pescheremo una connessione pronta dal pool
    public static Connection getConnection() throws SQLException {
        if (ds != null) {
            return ds.getConnection();
        }
        throw new SQLException("DataSource non configurato correttamente.");
    }
}