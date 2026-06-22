package it.lostvision.model.prodotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import it.lostvision.model.ConnessioneDB;

public class ProdottoDAOImpl implements ProdottoDAO {

	private static final String TABLE_NAME = "prodotto";

	@Override
	public synchronized void doSave(ProdottoBean prodotto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT IGNORE INTO " + ProdottoDAOImpl.TABLE_NAME
				+ " (ID_PRODOTTO, NOME_PRODOTTO, PREZZO, DESCRIZIONE, TIPOLOGIA, DISPONIBILITA) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = ConnessioneDB.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, prodotto.getIdProdotto());
			preparedStatement.setString(2, prodotto.getNomeProdotto());
			preparedStatement.setDouble(3, prodotto.getPrezzo());
			preparedStatement.setString(4, prodotto.getDescrizione());
			preparedStatement.setString(5, prodotto.getTipologia());
			preparedStatement.setInt(6, prodotto.getDisponibilita());

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
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProdottoDAOImpl.TABLE_NAME + " WHERE ID_PRODOTTO = ?";

		try {
			connection = ConnessioneDB.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized Collection<ProdottoBean> doRetrieveAll(String filter) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProdottoBean> products = new LinkedList<ProdottoBean>();

		String selectSQL = "SELECT * FROM " + ProdottoDAOImpl.TABLE_NAME;

		if (filter != null && !filter.equals("")) {
			selectSQL += " ORDER BY " + filter;
		}

		try {
			connection = ConnessioneDB.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProdottoBean bean = new ProdottoBean();

				bean.setIdProdotto(rs.getInt("ID_PRODOTTO"));
				bean.setNomeProdotto(rs.getString("NOME_PRODOTTO"));
				bean.setPrezzo(rs.getDouble("PREZZO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setTipologia(rs.getString("TIPOLOGIA"));
				bean.setDisponibilita(rs.getInt("DISPONIBILITA"));
				
				products.add(bean);
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
		return products;
	}
}