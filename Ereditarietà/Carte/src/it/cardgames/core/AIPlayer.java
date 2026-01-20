package it.cardgames.core;

import it.cardgames.model.Carta;
import java.util.Comparator;

public class AIPlayer extends Giocatore {

    public AIPlayer(String nome) { super(nome); }

    public Carta scegliCarta() {
        return getMano().stream()
                .max(Comparator.comparing(Carta::getNome))
                .orElse(null);
    }
}
