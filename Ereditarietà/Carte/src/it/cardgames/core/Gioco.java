package it.cardgames.core;

public interface Gioco {
    void inizia();
    void giocaTurno();
    boolean isFinito();
    String getStato();
}
