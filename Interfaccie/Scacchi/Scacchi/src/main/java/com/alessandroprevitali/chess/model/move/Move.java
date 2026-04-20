package main.java.com.alessandroprevitali.chess.model.move;

import main.java.com.alessandroprevitali.chess.model.board.Position;

public class Move {

    private final Position from;
    private final Position to;

    public Move(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Restituisce la posizione di origine della mossa.
     *
     * @return coordinata sorgente
     */
    public Position getFrom() {
        return from;
    }

    /**
     * Restituisce la posizione di destinazione della mossa.
     *
     * @return coordinata destinazione
     */
    public Position getTo() {
        return to;
    }

    /**
     * Restituisce una rappresentazione leggibile della mossa.
     *
     * @return stringa nel formato "from -> to"
     */
    @Override
    public String toString() {
        return from + " -> " + to;
    }
}
