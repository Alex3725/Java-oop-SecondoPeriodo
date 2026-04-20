package main.java.com.alessandroprevitali.chess.model.board;

import java.util.Objects;

public class Position {

    private final int row;
    private final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Restituisce la riga della posizione.
     *
     * @return indice di riga (0..7)
     */
    public int getRow() {
        return row;
    }

    /**
     * Restituisce la colonna della posizione.
     *
     * @return indice di colonna (0..7)
     */
    public int getCol() {
        return col;
    }

    /**
     * Confronta due posizioni in base a riga e colonna.
     *
     * @param other oggetto da confrontare
     * @return true se rappresenta la stessa coordinata, false altrimenti
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Position position)) {
            return false;
        }
        return row == position.row && col == position.col;
    }

    /**
     * Calcola hashCode coerente con equals su coppia (row, col).
     *
     * @return hash della posizione
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    /**
     * Restituisce una rappresentazione testuale compatta della posizione.
     *
     * @return stringa nel formato "(row,col)"
     */
    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }
}
