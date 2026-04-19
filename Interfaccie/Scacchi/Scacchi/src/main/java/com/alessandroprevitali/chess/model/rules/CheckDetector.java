package main.java.com.alessandroprevitali.chess.model.rules;

import main.java.com.alessandroprevitali.chess.model.board.Board;
import main.java.com.alessandroprevitali.chess.model.board.Position;
import main.java.com.alessandroprevitali.chess.model.move.Move;
import main.java.com.alessandroprevitali.chess.model.piece.Color;
import main.java.com.alessandroprevitali.chess.model.piece.Piece;
import main.java.com.alessandroprevitali.chess.model.piece.PieceType;

public class CheckDetector {

    public static boolean isInCheck(Color kingColor, Board board) {
        Position kingPos = findKing(kingColor, board);

        if (kingPos == null) {
            return false;
        }

        Color opponent = (kingColor == Color.WHITE) ? Color.BLACK : Color.WHITE;

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = board.getTile(r, c).getPiece();

                if (p != null && p.getColor() == opponent) {
                    for (Move m : p.getPseudoLegalMoves(board)) {
                        if (m.getTo().equals(kingPos)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static Position findKing(Color color, Board board) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = board.getTile(r, c).getPiece();

                if (p != null && p.getType() == PieceType.KING && p.getColor() == color) {
                    return new Position(r, c);
                }
            }
        }

        return null;
    }
}
