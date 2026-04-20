package main.java.com.alessandroprevitali.chess.model.rules;

import main.java.com.alessandroprevitali.chess.model.board.Board;
import main.java.com.alessandroprevitali.chess.model.piece.Color;
import main.java.com.alessandroprevitali.chess.service.MoveService;

public class CheckmateDetector {

    private CheckmateDetector() {
        // Utility class: no instances needed.
    }

    /**
     * Verifica se il colore indicato è in condizione di scacco matto.
     *
     * @param color colore del giocatore da analizzare
     * @param board board corrente
     * @param ms service per il calcolo delle mosse legali
     * @return true se il re è sotto scacco e non esistono mosse legali, false altrimenti
     */
    public static boolean isCheckmate(Color color, Board board, MoveService ms) {
        boolean isInCheck = CheckDetector.isInCheck(color, board);
        boolean hasLegalMoves = ms.hasAnyLegalMoves(color, board);
        return isInCheck && !hasLegalMoves;
    }
}
