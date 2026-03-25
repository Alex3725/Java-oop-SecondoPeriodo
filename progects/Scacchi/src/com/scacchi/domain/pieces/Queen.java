package com.scacchi.domain.pieces;

public class Queen extends Piece {
    public Queen(PieceColor color) {
        super(color);
    }

    @Override
    public char getSymbol() {
        return getColor() == PieceColor.WHITE ? 'Q' : 'q';
    }
}

