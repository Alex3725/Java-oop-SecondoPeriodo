package com.scacchi.logic.rules;

import com.scacchi.domain.board.Position;
import com.scacchi.domain.game.Game;
import com.scacchi.domain.pieces.Pawn;
import com.scacchi.domain.pieces.Piece;
import com.scacchi.domain.pieces.PieceColor;
import com.scacchi.domain.pieces.Queen;

public class PromotionHandler {
    public void applyPromotionIfNeeded(Game game, Position position) {
        Piece piece = game.getBoard().getPiece(position);
        if (!(piece instanceof Pawn)) {
            return;
        }

        if (piece.getColor() == PieceColor.WHITE && position.row() == 0) {
            game.getBoard().setPiece(position, new Queen(PieceColor.WHITE));
            return;
        }

        if (piece.getColor() == PieceColor.BLACK && position.row() == 7) {
            game.getBoard().setPiece(position, new Queen(PieceColor.BLACK));
        }
    }
}

