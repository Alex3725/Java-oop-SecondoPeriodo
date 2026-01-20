package it.cardgames.briscola;

import it.cardgames.core.Gioco;

public class BriscolaGioco implements Gioco {

    private int turno = 0;

    @Override
    public void inizia() { }

    @Override
    public void giocaTurno() { turno++; }

    @Override
    public boolean isFinito() { return turno >= 20; }

    @Override
    public String getStato() { return "Briscola | Turno " + turno; }
}
