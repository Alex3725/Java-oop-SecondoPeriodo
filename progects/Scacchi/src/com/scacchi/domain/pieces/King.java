package com.scacchi.domain.pieces;

public class King extends Piece {
    public King(PieceColor color) {
        super(color);
    }

    @Override
    public char getSymbol() {
        return getColor() == PieceColor.WHITE ? 'K' : 'k';
    }
}

