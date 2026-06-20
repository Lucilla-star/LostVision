package it.lostvision.model;

import java.io.Serializable;

public class Utente implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idUtente;
    private String nomeUtente;
    private String email;
    private String password;
    private String via;
    private int numeroCivico;
    private String cap;
    private String citta;
    private String provincia;
    private boolean isAdmin;

    public Utente() {
        this.idUtente = "";
        this.nomeUtente = "";
        this.email = "";
        this.password = "";
        this.via = "";
        this.numeroCivico = 0;
        this.cap = "";
        this.citta = "";
        this.provincia = "";
        this.isAdmin = false;
    }

    public String getIdUtente() { return idUtente; }
    public void setIdUtente(String idUtente) { this.idUtente = idUtente; }

    public String getNomeUtente() { return nomeUtente; }
    public void setNomeUtente(String nomeUtente) { this.nomeUtente = nomeUtente; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getVia() { return via; }
    public void setVia(String via) { this.via = via; }

    public int getNumeroCivico() { return numeroCivico; }
    public void setNumeroCivico(int numeroCivico) { this.numeroCivico = numeroCivico; }

    public String getCap() { return cap; }
    public void setCap(String cap) { this.cap = cap; }

    public String getCitta() { return citta; }
    public void setCitta(String citta) { this.citta = citta; }

    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean isAdmin) { this.isAdmin = isAdmin; }
}