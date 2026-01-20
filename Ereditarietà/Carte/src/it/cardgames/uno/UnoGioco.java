package it.cardgames.uno;

import it.cardgames.core.*;
import it.cardgames.model.banco.Banco;
import it.cardgames.persistence.GameState;
import java.util.List;

public class UnoGioco implements Gioco {

    private final Giocatore umano = new Giocatore("Umano");
    private final AIPlayer ai = new AIPlayer("AI");
    private final Banco banco = new Banco(new MazzoUno());
    private final TurnManager turni = new TurnManager(List.of(umano, ai));
    private int turno = 0;

    @Override
    public void inizia() {
        for (int i = 0; i < 7; i++) {
            umano.pesca(banco.pesca());
            ai.pesca(banco.pesca());
        }
    }

    @Override
    public void giocaTurno() {
        Giocatore g = turni.corrente();
        g.pesca(banco.pesca());
        turni.prossimo();
        turno++;
        GameState.save(getStato());
    }

    @Override
    public boolean isFinito() { return umano.carteInMano() == 0 || ai.carteInMano() == 0; }

    @Override
    public String getStato() {
        return "Turno " + turno + " | Umano: " + umano.carteInMano() + " | AI: " + ai.carteInMano();
    }
}
