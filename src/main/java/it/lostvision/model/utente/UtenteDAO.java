package it.lostvision.model.utente;

import java.sql.SQLException;

public interface UtenteDAO {

	public void doSave(UtenteBean utente) throws SQLException;

	public UtenteBean doRetrieveByEmail(String email) throws SQLException;
}