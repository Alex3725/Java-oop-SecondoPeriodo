package com.scacchi.domain.pieces;

public class Pawn extends Piece {
    public Pawn(PieceColor color) {
        super(color);
    }

    @Override
    public char getSymbol() {
        return getColor() == PieceColor.WHITE ? 'P' : 'p';
    }
}

