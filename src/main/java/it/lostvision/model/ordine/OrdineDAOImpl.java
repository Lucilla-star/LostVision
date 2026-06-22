package it.lostvision.model.ordine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import it.lostvision.model.ConnessioneDB;

public class OrdineDAOImpl implements OrdineDAO {

	private static final String TABLE_NAME = "ordine";

	@Override
	public synchronized void doSave(OrdineBean ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + OrdineDAOImpl.TABLE_NAME
				+ " (DATA_ORDINE, TOTALE, EMAIL) VALUES (?, ?, ?)";

		try {
			connection = ConnessioneDB.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setDate(1, ordine.getDataOrdine());
			preparedStatement.setDouble(2, ordine.getTotale());
			preparedStatement.setString(3, ordine.getEmailUtente());

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
	public synchronized Collection<OrdineBean> doRetrieveByUtente(String emailUtente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();

		String selectSQL = "SELECT * FROM " + OrdineDAOImpl.TABLE_NAME + " WHERE EMAIL = ?";

		try {
			connection = ConnessioneDB.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, emailUtente);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				bean.setIdOrdine(rs.getInt("ID_ORDINE"));
				bean.setDataOrdine(rs.getDate("DATA_ORDINE"));
				bean.setTotale(rs.getDouble("TOTALE"));
				bean.setEmailUtente(rs.getString("EMAIL"));
				
				ordini.add(bean);
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
		return ordini;
	}
}