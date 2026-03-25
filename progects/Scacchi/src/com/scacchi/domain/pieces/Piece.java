package com.scacchi.domain.pieces;

public abstract class Piece {
    private final PieceColor color;

    protected Piece(PieceColor color) {
        this.color = color;
    }

    public PieceColor getColor() {
        return color;
    }

    public abstract char getSymbol();
}

