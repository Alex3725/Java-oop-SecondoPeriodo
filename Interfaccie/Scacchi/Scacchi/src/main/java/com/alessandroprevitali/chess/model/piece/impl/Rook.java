package main.java.com.alessandroprevitali.chess.model.piece.impl;
import main.java.com.alessandroprevitali.chess.model.board.Board;
import main.java.com.alessandroprevitali.chess.model.board.Position;
import main.java.com.alessandroprevitali.chess.model.move.Move;
import main.java.com.alessandroprevitali.chess.model.piece.Color;
import main.java.com.alessandroprevitali.chess.model.piece.Piece;
import main.java.com.alessandroprevitali.chess.model.piece.PieceType;
import java.util.ArrayList;
import java.util.List;
public class Rook extends Piece {

    public Rook(Color color, Position position) {
        super(color, position);
    }

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }

    @Override
    public List<Move> getPseudoLegalMoves(Board board) {
        List<Move> moves = new ArrayList<>();

        addLineMoves(board, moves, 0, 1);
        addLineMoves(board, moves, 0, -1);
        addLineMoves(board, moves, 1, 0);
        addLineMoves(board, moves, -1, 0);

        return moves;
    }

    private void addLineMoves(Board board, List<Move> moves, int rowStep, int colStep) {
        int row = position.getRow() + rowStep;
        int col = position.getCol() + colStep;

        while (isInsideBoard(row, col)) {
            Piece target = board.getTile(row, col).getPiece();

            if (target == null) {
                moves.add(new Move(position, new Position(row, col)));
            } else {
                if (target.getColor() != color) {
                    moves.add(new Move(position, new Position(row, col)));
                }
                break;
            }

            row += rowStep;
            col += colStep;
        }
    }

    private boolean isInsideBoard(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}
