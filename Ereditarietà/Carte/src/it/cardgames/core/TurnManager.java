package it.cardgames.core;

import java.util.List;

public class TurnManager {

    private final List<Giocatore> giocatori;
    private int indice = 0;
    private int direzione = 1;

    public TurnManager(List<Giocatore> giocatori) {
        this.giocatori = giocatori;
    }

    public Giocatore corrente() { return giocatori.get(indice); }

    public void prossimo() { indice = (indice + direzione + giocatori.size()) % giocatori.size(); }

    public void inverti() { direzione *= -1; }
}
