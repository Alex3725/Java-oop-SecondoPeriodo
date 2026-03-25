package com.scacchi.logic.move;

import com.scacchi.domain.board.Board;
import com.scacchi.domain.board.Position;
import com.scacchi.domain.game.Game;
import com.scacchi.domain.game.Move;
import com.scacchi.domain.pieces.Pawn;
import com.scacchi.domain.pieces.Piece;
import com.scacchi.domain.pieces.PieceColor;

public class MoveValidator {
    public ValidationResult validate(Game game, Move move) {
        Position from = move.getFrom();
        Position to = move.getTo();

        if (!from.isInsideBoard() || !to.isInsideBoard()) {
            return ValidationResult.fail("Posizione fuori dalla scacchiera");
        }

        if (from.row() == to.row() && from.col() == to.col()) {
            return ValidationResult.fail("La mossa non cambia posizione");
        }

        Board board = game.getBoard();
        Piece source = board.getPiece(from);
        if (source == null) {
            return ValidationResult.fail("Nessun pezzo nella casella di partenza");
        }

        if (source.getColor() != game.getCurrentPlayer()) {
            return ValidationResult.fail("Non e il turno di quel pezzo");
        }

        Piece target = board.getPiece(to);
        if (target != null && target.getColor() == source.getColor()) {
            return ValidationResult.fail("Non puoi catturare un tuo pezzo");
        }

        if (source instanceof Pawn) {
            return validatePawnMove((Pawn) source, from, to, target, board);
        }

        // Placeholder: per ora i pezzi non-pawn hanno regole semplificate.
        return ValidationResult.ok();
    }

    private ValidationResult validatePawnMove(Pawn pawn, Position from, Position to, Piece target, Board board) {
        int direction = pawn.getColor() == PieceColor.WHITE ? -1 : 1;
        int startRow = pawn.getColor() == PieceColor.WHITE ? 6 : 1;

        int rowDelta = to.row() - from.row();
        int colDelta = Math.abs(to.col() - from.col());

        if (colDelta == 0) {
            if (target != null) {
                return ValidationResult.fail("Il pedone non puo avanzare su una casella occupata");
            }
            if (rowDelta == direction) {
                return ValidationResult.ok();
            }
            if (from.row() == startRow && rowDelta == 2 * direction) {
                Position middle = new Position(from.row() + direction, from.col());
                if (board.getPiece(middle) == null) {
                    return ValidationResult.ok();
                }
                return ValidationResult.fail("Pedone bloccato sulla casella intermedia");
            }
            return ValidationResult.fail("Movimento pedone non valido");
        }

        if (colDelta == 1 && rowDelta == direction && target != null) {
            return ValidationResult.ok();
        }

        return ValidationResult.fail("Movimento pedone non valido");
    }
}

