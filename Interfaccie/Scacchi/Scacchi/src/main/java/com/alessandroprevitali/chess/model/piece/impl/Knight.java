package main.java.com.alessandroprevitali.chess.model.piece.impl;
import main.java.com.alessandroprevitali.chess.model.board.Board;
import main.java.com.alessandroprevitali.chess.model.board.Position;
import main.java.com.alessandroprevitali.chess.model.move.Move;
import main.java.com.alessandroprevitali.chess.model.piece.Color;
import main.java.com.alessandroprevitali.chess.model.piece.Piece;
import main.java.com.alessandroprevitali.chess.model.piece.PieceType;
import java.util.ArrayList;
import java.util.List;
public class Knight extends Piece {
    public Knight(Color color, Position position) {
        super(color, position);
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }

    @Override
    public List<Move> getPseudoLegalMoves(Board board) {
        List<Move> moves = new ArrayList<>();

        tryAddKnightMove(board, moves, -2, -1);
        tryAddKnightMove(board, moves, -2, 1);
        tryAddKnightMove(board, moves, -1, -2);
        tryAddKnightMove(board, moves, -1, 2);
        tryAddKnightMove(board, moves, 1, -2);
        tryAddKnightMove(board, moves, 1, 2);
        tryAddKnightMove(board, moves, 2, -1);
        tryAddKnightMove(board, moves, 2, 1);

        return moves;
    }

    private void tryAddKnightMove(Board board, List<Move> moves, int rowStep, int colStep) {
        int row = position.getRow() + rowStep;
        int col = position.getCol() + colStep;

        if (!isInsideBoard(row, col)) {
            return;
        }

        Piece target = board.getTile(row, col).getPiece();
        if (target == null || target.getColor() != color) {
            moves.add(new Move(position, new Position(row, col)));
        }
    }

    private boolean isInsideBoard(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}
