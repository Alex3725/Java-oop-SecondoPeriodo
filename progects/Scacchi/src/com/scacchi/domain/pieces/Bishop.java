package com.scacchi.domain.pieces;

public class Bishop extends Piece {
    public Bishop(PieceColor color) {
        super(color);
    }

    @Override
    public char getSymbol() {
        return getColor() == PieceColor.WHITE ? 'B' : 'b';
    }
}

