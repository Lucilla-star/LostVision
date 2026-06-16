package it.lostvision.model;

import java.io.Serializable;

// Implementiamo Serializable perché i dati del Model devono poter viaggiare sul web
public class Prodotto implements Serializable {
    private static final long serialVersionUID = 1L;

    // Queste variabili sono le stesse colonne della tabella Prodotto nel DB
    private String idProdotto;
    private String nomeProdotto;
    private String descrizione;
    private double prezzo;
    private String taglia;
    private String immagine;
    private double iva;
    private String idCategoria;

    // 1. Costruttore vuoto (obbligatorio per i JavaBean)
    public Prodotto() {
        this.idProdotto = "";
        this.nomeProdotto = "";
        this.descrizione = "";
        this.prezzo = 0.0;
        this.taglia = "";
        this.immagine = "";
        this.iva = 22.0; // Valore di default
        this.idCategoria = "";
    }

    // 2. I metodi GETTER e SETTER (servono a leggere e scrivere queste variabili da fuori)
    public String getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(String idProdotto) {
        this.idProdotto = idProdotto;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getTaglia() {
        return taglia;
    }

    public void setTaglia(String taglia) {
        this.taglia = taglia;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }
}