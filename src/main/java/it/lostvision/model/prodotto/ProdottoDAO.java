package it.lostvision.model.prodotto;

import java.sql.SQLException;
import java.util.Collection;

public interface ProdottoDAO {

	public void doSave(ProdottoBean prodotto) throws SQLException;

	public boolean doDelete(int code) throws SQLException;

	public Collection<ProdottoBean> doRetrieveAll(String filter) throws SQLException;
}