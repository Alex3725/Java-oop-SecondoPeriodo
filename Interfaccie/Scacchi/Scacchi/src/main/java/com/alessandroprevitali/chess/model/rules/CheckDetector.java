package main.java.com.alessandroprevitali.chess.model.rules;

import main.java.com.alessandroprevitali.chess.model.board.Board;
import main.java.com.alessandroprevitali.chess.model.board.Position;
import main.java.com.alessandroprevitali.chess.model.move.Move;
import main.java.com.alessandroprevitali.chess.model.piece.Color;
import main.java.com.alessandroprevitali.chess.model.piece.Piece;
import main.java.com.alessandroprevitali.chess.model.piece.PieceType;

public class CheckDetector {

    /**
     * Determina se il re del colore indicato è sotto attacco nella board corrente.
     *
     * @param kingColor colore del re da controllare
     * @param board board da analizzare
     * @return true se almeno una pseudo-mossa avversaria raggiunge il re, false altrimenti
     */
    public static boolean isInCheck(Color kingColor, Board board) {
        // Trova prima il re da controllare, poi verifica se qualche pezzo avversario lo attacca.
        Position kingPos = findKing(kingColor, board);

        if (kingPos == null) {
            return false;
        }

        Color opponent = (kingColor == Color.WHITE) ? Color.BLACK : Color.WHITE;

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = board.getTile(r, c).getPiece();

                if (p != null && p.getColor() == opponent) {
                    // Le pseudo-mosse sono sufficienti: qui interessa solo sapere se il re è raggiungibile.
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

    /**
     * Trova la posizione del re del colore richiesto.
     *
     * @param color colore del re da cercare
     * @param board board da analizzare
     * @return posizione del re se presente, null se non trovato
     */
    public static Position findKing(Color color, Board board) {
        // Scansione lineare della board per trovare il re del colore richiesto.
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
