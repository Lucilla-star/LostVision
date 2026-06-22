package it.lostvision.model.carrello;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import it.lostvision.model.prodotto.ProdottoBean;

public class CarrelloBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ArticoloCarrelloBean> articoli;

	public CarrelloBean() {
		this.articoli = new ArrayList<>();
	}

	public List<ArticoloCarrelloBean> getArticoli() {
		return articoli;
	}

	public void aggiungiArticolo(ProdottoBean prodotto, int quantita) {
		for (ArticoloCarrelloBean art : articoli) {
			if (art.getProdotto().getIdProdotto() == prodotto.getIdProdotto()) {
				art.setQuantita(art.getQuantita() + quantita);
				return;
			}
		}
		articoli.add(new ArticoloCarrelloBean(prodotto, quantita));
	}

	public void rimuoviArticolo(int idProdotto) {
		articoli.removeIf(art -> art.getProdotto().getIdProdotto() == idProdotto);
	}

	public double getTotaleComplessivo() {
		double totale = 0.0;
		for (ArticoloCarrelloBean art : articoli) {
			totale += art.getPrezzoTotale();
		}
		return totale;
	}

	public void svuota() {
		articoli.clear();
	}
}