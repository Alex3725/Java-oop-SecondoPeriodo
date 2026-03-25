package com.scacchi.domain.pieces;

public class Knight extends Piece {
    public Knight(PieceColor color) {
        super(color);
    }

    @Override
    public char getSymbol() {
        return getColor() == PieceColor.WHITE ? 'N' : 'n';
    }
}

