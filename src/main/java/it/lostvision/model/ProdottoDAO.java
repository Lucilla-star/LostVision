package it.lostvision.model;

import java.sql.SQLException;
import java.util.Collection;

public interface ProdottoDAO {
    
    // Salva un nuovo prodotto nel database
    public void doSave(Prodotto prodotto) throws SQLException;
    
    // Cancella un prodotto conoscendo il suo ID
    public boolean doDelete(String idProdotto) throws SQLException;
    
    // Cerca un singolo prodotto tramite il suo ID (fondamentale per la pagina di dettaglio!)
    public Prodotto doRetrieveByKey(String idProdotto) throws SQLException;
    
    // Prende TUTTI i prodotti dal DB (fondamentale per il catalogo/shop!)
    public Collection<Prodotto> doRetrieveAll(String order) throws SQLException;
}