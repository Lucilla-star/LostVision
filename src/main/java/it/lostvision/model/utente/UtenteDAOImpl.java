package it.lostvision.model.utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.lostvision.model.ConnessioneDB;

public class UtenteDAOImpl implements UtenteDAO {

	private static final String TABLE_NAME = "utente";

	@Override
	public synchronized void doSave(UtenteBean utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + UtenteDAOImpl.TABLE_NAME
				+ " (EMAIL, NOME, COGNOME, PASSWORD, RUOLO) VALUES (?, ?, ?, ?, ?)";

		try {
			connection = ConnessioneDB.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, utente.getEmail());
			preparedStatement.setString(2, utente.getNome());
			preparedStatement.setString(3, utente.getCognome());
			preparedStatement.setString(4, utente.getPassword());
			preparedStatement.setString(5, utente.getRuolo());

			preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}

	@Override
	public synchronized UtenteBean doRetrieveByEmail(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UtenteBean bean = null;

		String selectSQL = "SELECT * FROM " + UtenteDAOImpl.TABLE_NAME + " WHERE EMAIL = ?";

		try {
			connection = ConnessioneDB.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				bean = new UtenteBean();
				bean.setEmail(rs.getString("EMAIL"));
				bean.setNome(rs.getString("NOME"));
				bean.setCognome(rs.getString("COGNOME"));
				bean.setPassword(rs.getString("PASSWORD"));
				bean.setRuolo(rs.getString("RUOLO"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}
}