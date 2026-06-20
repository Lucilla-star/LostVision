package it.lostvision.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import it.lostvision.model.ConnessioneDB; // <--- Cambia con la tua classe di connessione se si chiama in un altro modo

public class OrdineDAOImpl implements OrdineDAO {

    @Override
    public void doSave(Ordine ordine) throws SQLException {
        Connection connection = null;
        PreparedStatement psOrdine = null;
        PreparedStatement psDettaglio = null;

        String insertOrdine = "INSERT INTO Ordine (id_ordine, data_ordine, totale, stato, id_utente) VALUES (?, ?, ?, ?, ?)";
        String insertDettaglio = "INSERT INTO DettaglioOrdine (id_dettaglio, id_ordine, id_prodotto, quantita, prezzo, iva) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            connection = ConnessioneDB.getConnection(); // <--- Cambia qui se usi un metodo diverso per la connessione
            connection.setAutoCommit(false); // Attiviamo la transazione sicura

            // 1. Generiamo ID Ordine unico (6 caratteri)
            String idOrdine = "ORD" + UUID.randomUUID().toString().substring(0, 3).toUpperCase();
            ordine.setIdOrdine(idOrdine);

            psOrdine = connection.prepareStatement(insertOrdine);
            psOrdine.setString(1, ordine.getIdOrdine());
            psOrdine.setDate(2, ordine.getDataOrdine());
            psOrdine.setDouble(3, ordine.getTotale());
            psOrdine.setString(4, ordine.getStato());
            psOrdine.setString(5, ordine.getIdUtente());
            psOrdine.executeUpdate();

            // 2. Inseriamo i singoli dettagli
            psDettaglio = connection.prepareStatement(insertDettaglio);
            for (ArticoloCarrello art : ordine.getDettagli()) {
                String idDettaglio = "DET" + UUID.randomUUID().toString().substring(0, 3).toUpperCase();
                
                psDettaglio.setString(1, idDettaglio);
                psDettaglio.setString(2, ordine.getIdOrdine());
                psDettaglio.setString(3, art.getProdotto().getIdProdotto());
                psDettaglio.setInt(4, art.getQuantita());
                psDettaglio.setDouble(5, art.getProdotto().getPrezzo());
                psDettaglio.setDouble(6, art.getProdotto().getIva());
                psDettaglio.addBatch(); // Ottimizza l'inserimento multiplo
            }
            psDettaglio.executeBatch();

            connection.commit(); // Salviamo tutto definitivamente nel DB!
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback(); // Se c'è un errore, annulla tutto per non sporcare il database
            }
            throw e;
        } finally {
            if (psOrdine != null) psOrdine.close();
            if (psDettaglio != null) psDettaglio.close();
            if (connection != null) connection.close();
        }
    }
}