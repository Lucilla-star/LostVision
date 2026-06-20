package it.lostvision.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAOImpl implements UtenteDAO {

    private static final String TABLE_NAME = "Utente";

    @Override
    public synchronized void doSave(Utente utente) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        // Query per inserire tutti i campi dell'utente nel DB durante la registrazione
        String insertSQL = "INSERT INTO " + TABLE_NAME
                + " (id_utente, nome_utente, email, password, via, numero_civico, cap, citta, provincia, is_admin) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = ConnessioneDB.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            
            preparedStatement.setString(1, utente.getIdUtente());
            preparedStatement.setString(2, utente.getNomeUtente());
            preparedStatement.setString(3, utente.getEmail());
            preparedStatement.setString(4, utente.getPassword()); // In futuro la cifreremo, per ora va bene in chiaro per l'esame
            preparedStatement.setString(5, utente.getVia());
            preparedStatement.setInt(6, utente.getNumeroCivico());
            preparedStatement.setString(7, utente.getCap());
            preparedStatement.setString(8, utente.getCitta());
            preparedStatement.setString(9, utente.getProvincia());
            preparedStatement.setBoolean(10, utente.isAdmin());

            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }

    @Override
    public synchronized Utente doRetrieveByLogin(String email, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Utente utente = null;

        // Query di Login: cerca un utente che abbia la combinazione esatta di email e password
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE email = ? AND password = ?";

        try {
            connection = ConnessioneDB.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                utente = new Utente();
                utente.setIdUtente(rs.getString("id_utente"));
                utente.setNomeUtente(rs.getString("nome_utente"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
                utente.setVia(rs.getString("via"));
                utente.setNumeroCivico(rs.getInt("numero_civico"));
                utente.setCap(rs.getString("cap"));
                utente.setCitta(rs.getString("citta"));
                utente.setProvincia(rs.getString("provincia"));
                utente.setAdmin(rs.getBoolean("is_admin"));
            }
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
        return utente; // Se non trova nessuno restituisce null, altrimenti restituisce l'utente loggato
    }
}