package it.lostvision.model.ordine;

import java.io.Serializable;
import java.sql.Date;

public class OrdineBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idOrdine;
	private Date dataOrdine;
	private double totale;
	private String emailUtente;

	public OrdineBean() {
		this.idOrdine = -1;
		this.dataOrdine = new Date(System.currentTimeMillis());
		this.totale = 0.0;
		this.emailUtente = "";
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public Date getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public String getEmailUtente() {
		return emailUtente;
	}

	public void setEmailUtente(String emailUtente) {
		this.emailUtente = emailUtente;
	}
}