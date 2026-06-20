package it.lostvision.model;

import java.sql.SQLException;

public interface OrdineDAO {
    void doSave(Ordine ordine) throws SQLException;
}