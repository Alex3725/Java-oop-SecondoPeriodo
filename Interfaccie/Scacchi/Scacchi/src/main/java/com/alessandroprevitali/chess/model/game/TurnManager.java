package main.java.com.alessandroprevitali.chess.model.game;

public class TurnManager {

    // Bianco inizia sempre la partita.
    private boolean whiteTurn = true;

    /**
     * Indica se il turno corrente appartiene al bianco.
     *
     * @return true se è il turno del bianco, false se è del nero
     */
    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    /**
     * Inverte il turno corrente (bianco <-> nero).
     */
    public void switchTurn() {
        // Inverte il turno dopo una mossa valida.
        whiteTurn = !whiteTurn;
    }

    /**
     * Reimposta il turno iniziale al bianco.
     */
    public void reset() {
        // Riporta il turno al bianco per una nuova partita.
        whiteTurn = true;
    }
}
