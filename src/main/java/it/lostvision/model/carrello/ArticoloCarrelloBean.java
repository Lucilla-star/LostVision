package it.lostvision.model.carrello;

import java.io.Serializable;
import it.lostvision.model.prodotto.ProdottoBean;

public class ArticoloCarrelloBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ProdottoBean prodotto;
	private int quantita;

	public ArticoloCarrelloBean() {
		this.prodotto = null;
		this.quantita = 0;
	}

	public ArticoloCarrelloBean(ProdottoBean prodotto, int quantita) {
		this.prodotto = prodotto;
		this.quantita = quantita;
	}

	public ProdottoBean getProdotto() {
		return prodotto;
	}

	public void setProdotto(ProdottoBean prodotto) {
		this.prodotto = prodotto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getPrezzoTotale() {
		if (prodotto != null) {
			return prodotto.getPrezzo() * quantita;
		}
		return 0.0;
	}
}