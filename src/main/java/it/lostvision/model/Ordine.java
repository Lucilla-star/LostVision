package it.lostvision.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Ordine {
    private String idOrdine;
    private Date dataOrdine;
    private double totale;
    private String stato;
    private String idUtente;
    private List<ArticoloCarrello> dettagli = new ArrayList<>();

    // Costruttore vuoto
    public Ordine() {}

    // Getter e Setter
    public String getIdOrdine() { return idOrdine; }
    public void setIdOrdine(String idOrdine) { this.idOrdine = idOrdine; }
    public Date getDataOrdine() { return dataOrdine; }
    public void setDataOrdine(Date dataOrdine) { this.dataOrdine = dataOrdine; }
    public double getTotale() { return totale; }
    public void setTotale(double totale) { this.totale = totale; }
    public String getStato() { return stato; }
    public void setStato(String stato) { this.stato = stato; }
    public String getIdUtente() { return idUtente; }
    public void setIdUtente(String idUtente) { this.idUtente = idUtente; }
    public List<ArticoloCarrello> getDettagli() { return dettagli; }
    public void setDettagli(List<ArticoloCarrello> dettagli) { this.dettagli = dettagli; }
}