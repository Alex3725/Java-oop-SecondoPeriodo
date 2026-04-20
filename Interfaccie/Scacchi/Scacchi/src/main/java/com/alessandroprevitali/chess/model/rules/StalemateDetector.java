package main.java.com.alessandroprevitali.chess.model.rules;

import main.java.com.alessandroprevitali.chess.model.board.Board;
import main.java.com.alessandroprevitali.chess.model.piece.Color;
import main.java.com.alessandroprevitali.chess.service.MoveService;

public class StalemateDetector {

    private StalemateDetector() {
        // Utility class: no instances needed.
    }

    /**
     * Verifica se il colore indicato è in condizione di stallo.
     *
     * @param color colore del giocatore da analizzare
     * @param board board corrente
     * @param ms service per il calcolo delle mosse legali
     * @return true se non è in scacco e non ha mosse legali, false altrimenti
     */
    public static boolean isStalemate(Color color, Board board, MoveService ms) {
        boolean isInCheck = CheckDetector.isInCheck(color, board);
        boolean hasLegalMoves = ms.hasAnyLegalMoves(color, board);
        return !isInCheck && !hasLegalMoves;
    }
}
