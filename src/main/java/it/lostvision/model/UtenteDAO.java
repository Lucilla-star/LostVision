package it.lostvision.model;

import java.sql.SQLException;

public interface UtenteDAO {
    
    // Serve per la Registrazione: salva un nuovo utente nel DB
    public void doSave(Utente utente) throws SQLException;
    
    // Serve per il Login: cerca se esiste un utente con questa email e password
    public Utente doRetrieveByLogin(String email, String password) throws SQLException;
}
