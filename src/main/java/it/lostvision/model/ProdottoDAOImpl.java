package it.lostvision.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class ProdottoDAOImpl implements ProdottoDAO {

    private static final String TABLE_NAME = "Prodotto"; 

    @Override
    public synchronized Prodotto doRetrieveByKey(String idProdotto) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Prodotto bean = new Prodotto();

        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id_prodotto = ?";

        try {
            connection = ConnessioneDB.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, idProdotto);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                bean.setIdProdotto(rs.getString("id_prodotto"));
                bean.setNomeProdotto(rs.getString("nome_prodotto"));
                bean.setDescrizione(rs.getString("descrizione"));
                bean.setPrezzo(rs.getDouble("prezzo"));
                bean.setTaglia(rs.getString("taglia"));
                bean.setImmagine(rs.getString("immagine"));
                bean.setIva(rs.getDouble("iva"));
                bean.setIdCategoria(rs.getString("id_categoria"));
            }

        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
        return bean;
    }

    @Override
    public synchronized Collection<Prodotto> doRetrieveAll(String order) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Collection<Prodotto> products = new LinkedList<Prodotto>();

        String selectSQL = "SELECT * FROM " + TABLE_NAME;

        if (order != null && !order.equals("")) {
            selectSQL += " ORDER BY " + order;
        }

        try {
            connection = ConnessioneDB.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Prodotto bean = new Prodotto();
                bean.setIdProdotto(rs.getString("id_prodotto"));
                bean.setNomeProdotto(rs.getString("nome_prodotto"));
                bean.setDescrizione(rs.getString("descrizione"));
                bean.setPrezzo(rs.getDouble("prezzo"));
                bean.setTaglia(rs.getString("taglia"));
                bean.setImmagine(rs.getString("immagine"));
                bean.setIva(rs.getDouble("iva"));
                bean.setIdCategoria(rs.getString("id_categoria"));
                
                products.add(bean);
            }

        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
        return products;
    }

    @Override
    public synchronized void doSave(Prodotto prodotto) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " + TABLE_NAME
                + " (id_prodotto, nome_prodotto, descrizione, prezzo, taglia, immagine, iva, id_categoria) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = ConnessioneDB.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, prodotto.getIdProdotto());
            preparedStatement.setString(2, prodotto.getNomeProdotto());
            preparedStatement.setString(3, prodotto.getDescrizione());
            preparedStatement.setDouble(4, prodotto.getPrezzo());
            preparedStatement.setString(5, prodotto.getTaglia());
            preparedStatement.setString(6, prodotto.getImmagine());
            preparedStatement.setDouble(7, prodotto.getIva());
            preparedStatement.setString(8, prodotto.getIdCategoria());

            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }

    @Override
    public synchronized boolean doDelete(String idProdotto) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = 0;

        String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE id_prodotto = ?";

        try {
            connection = ConnessioneDB.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setString(1, idProdotto);

            result = preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
        return (result != 0);
    }
}