package com.scacchi.domain.pieces;

public class Rook extends Piece {
    public Rook(PieceColor color) {
        super(color);
    }

    @Override
    public char getSymbol() {
        return getColor() == PieceColor.WHITE ? 'R' : 'r';
    }
}

