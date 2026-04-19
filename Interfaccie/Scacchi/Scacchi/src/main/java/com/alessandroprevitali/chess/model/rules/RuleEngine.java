package main.java.com.alessandroprevitali.chess.model.rules;

import main.java.com.alessandroprevitali.chess.model.board.Board;
import main.java.com.alessandroprevitali.chess.model.game.GameState;
import main.java.com.alessandroprevitali.chess.model.piece.Color;
import main.java.com.alessandroprevitali.chess.service.MoveService;

public class RuleEngine {

    private RuleEngine() {
        // Utility class: no instances needed.
    }

    public static GameState evaluate(Color turn, Board board, MoveService ms) {
        boolean inCheck = CheckDetector.isInCheck(turn, board);
        boolean hasLegalMoves = ms.hasAnyLegalMoves(turn, board);

        if (!hasLegalMoves) {
            if (inCheck) {
                return GameState.CHECKMATE;
            }
            return GameState.STALEMATE;
        }

        if (inCheck) {
            return GameState.CHECK;
        }

        return GameState.ONGOING;
    }
}
