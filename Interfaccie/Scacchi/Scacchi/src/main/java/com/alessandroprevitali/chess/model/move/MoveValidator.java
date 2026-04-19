package main.java.com.alessandroprevitali.chess.model.move;

import main.java.com.alessandroprevitali.chess.model.board.Board;

public interface MoveValidator {
    boolean isValid(Move move, Board board);
}
