package main.java.com.alessandroprevitali.chess.model.piece;

import main.java.com.alessandroprevitali.chess.model.board.Board;
import main.java.com.alessandroprevitali.chess.model.board.Position;
import main.java.com.alessandroprevitali.chess.model.move.Move;

import java.util.List;

public abstract class Piece {
    protected Color color;
    protected Position position;

    public abstract List<Move> getLegalMoves(Board board);
}
