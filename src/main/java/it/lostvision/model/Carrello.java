package it.lostvision.model;

import java.util.ArrayList;
import java.util.List;

public class Carrello {
    private List<ArticoloCarrello> articoli;

    public Carrello() {
        this.articoli = new ArrayList<>();
    }

    public List<ArticoloCarrello> getArticoli() {
        return articoli;
    }

    // Metodo per aggiungere un prodotto al carrello
    public void aggiungiProdotto(Prodotto prodotto, int quantita) {
        // Controlliamo se il prodotto è già presente nel carrello
        for (ArticoloCarrello art : articoli) {
            if (art.getProdotto().getIdProdotto().equals(prodotto.getIdProdotto())) {
                // Se c'è già, aumentiamo solo la quantità
                art.setQuantita(art.getQuantita() + quantita);
                return;
            }
        }
        // Se non c'era, lo aggiungiamo come nuovo articolo
        articoli.add(new ArticoloCarrello(prodotto, quantita));
    }

    // Metodo per rimuovere un prodotto dal carrello
    public void rimuoviProdotto(String idProdotto) {
        articoli.removeIf(art -> art.getProdotto().getIdProdotto().equals(idProdotto));
    }

    // Metodo per calcolare il prezzo totale di tutto il carrello
    public double getTotaleComplessivo() {
        double totale = 0;
        for (ArticoloCarrello art : articoli) {
            totale += art.getPrezzoTotale();
        }
        return totale;
    }
}