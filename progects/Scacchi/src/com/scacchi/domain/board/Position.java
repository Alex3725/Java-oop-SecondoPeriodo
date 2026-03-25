package com.scacchi.domain.board;

import com.scacchi.util.Constants;

public class Position {
    private final int row;
    private final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }

    public boolean isInsideBoard() {
        return row >= 0 && row < Constants.BOARD_SIZE && col >= 0 && col < Constants.BOARD_SIZE;
    }

    public static Position fromAlgebraic(String value) {
        if (value == null || value.length() != 2) {
            throw new IllegalArgumentException("Invalid position: " + value);
        }
        char file = Character.toLowerCase(value.charAt(0));
        char rank = value.charAt(1);
        if (file < 'a' || file > 'h' || rank < '1' || rank > '8') {
            throw new IllegalArgumentException("Invalid position: " + value);
        }
        int col = file - 'a';
        int row = Constants.BOARD_SIZE - (rank - '0');
        return new Position(row, col);
    }

    @Override
    public String toString() {
        char file = (char) ('a' + col);
        int rank = Constants.BOARD_SIZE - row;
        return "" + file + rank;
    }
}

