package it.lostvision.model.ordine;

import java.sql.SQLException;
import java.util.Collection;

public interface OrdineDAO {

	public void doSave(OrdineBean ordine) throws SQLException;

	public Collection<OrdineBean> doRetrieveByUtente(String emailUtente) throws SQLException;
}