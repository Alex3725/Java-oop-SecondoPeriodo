package main.java.com.alessandroprevitali.chess.model.board;

import main.java.com.alessandroprevitali.chess.model.piece.Piece;

public class Tile {

    private Position position;
    private Piece piece;

    public Tile(Position position) {
        this.position = position;
    }

    /**
     * Restituisce il pezzo presente nella casella.
     *
     * @return pezzo presente oppure null se la casella è vuota
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Imposta il pezzo contenuto nella casella.
     *
     * @param piece pezzo da assegnare oppure null per svuotare la casella
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Restituisce la posizione della casella.
     *
     * @return posizione associata alla tile
     */
    public Position getPosition() {
        return position;
    }
}
