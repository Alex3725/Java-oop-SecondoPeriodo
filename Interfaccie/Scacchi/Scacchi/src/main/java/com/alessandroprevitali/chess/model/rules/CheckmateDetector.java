package main.java.com.alessandroprevitali.chess.model.rules;

import main.java.com.alessandroprevitali.chess.model.board.Board;
import main.java.com.alessandroprevitali.chess.model.piece.Color;
import main.java.com.alessandroprevitali.chess.service.MoveService;

public class CheckmateDetector {

    private CheckmateDetector() {
        // Utility class: no instances needed.
    }

    public static boolean isCheckmate(Color color, Board board, MoveService ms) {
        boolean isInCheck = CheckDetector.isInCheck(color, board);
        boolean hasLegalMoves = ms.hasAnyLegalMoves(color, board);
        return isInCheck && !hasLegalMoves;
    }
}
