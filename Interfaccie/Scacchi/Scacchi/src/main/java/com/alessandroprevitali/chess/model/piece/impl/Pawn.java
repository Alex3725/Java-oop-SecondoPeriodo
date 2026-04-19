package main.java.com.alessandroprevitali.chess.model.piece.impl;
import main.java.com.alessandroprevitali.chess.model.board.Board;
import main.java.com.alessandroprevitali.chess.model.board.Position;
import main.java.com.alessandroprevitali.chess.model.move.Move;
import main.java.com.alessandroprevitali.chess.model.piece.Color;
import main.java.com.alessandroprevitali.chess.model.piece.Piece;
import main.java.com.alessandroprevitali.chess.model.piece.PieceType;
import java.util.ArrayList;
import java.util.List;
public class Pawn extends Piece {
    public Pawn(Color color, Position position) {
        super(color, position);
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }

    @Override
    public List<Move> getPseudoLegalMoves(Board board) {
        List<Move> moves = new ArrayList<>();

        int direction = (color == Color.WHITE) ? -1 : 1;
        int startRow = (color == Color.WHITE) ? 6 : 1;

        addForwardMoves(board, moves, direction, startRow);
        addDiagonalCaptures(board, moves, direction);

        return moves;
    }

    private void addForwardMoves(Board board, List<Move> moves, int direction, int startRow) {
        int row = position.getRow();
        int col = position.getCol();
        int nextRow = row + direction;

        if (!isInsideBoard(nextRow, col)) {
            return;
        }

        if (board.getTile(nextRow, col).getPiece() != null) {
            return;
        }

        moves.add(new Move(position, new Position(nextRow, col)));

        int twoStepsRow = row + (2 * direction);
        boolean onStartRow = row == startRow;
        if (onStartRow && isInsideBoard(twoStepsRow, col) && board.getTile(twoStepsRow, col).getPiece() == null) {
            moves.add(new Move(position, new Position(twoStepsRow, col)));
        }
    }

    private void addDiagonalCaptures(Board board, List<Move> moves, int direction) {
        int row = position.getRow();
        int col = position.getCol();
        int targetRow = row + direction;

        int[] diagonalCols = {-1, 1};
        for (int deltaCol : diagonalCols) {
            int targetCol = col + deltaCol;

            if (!isInsideBoard(targetRow, targetCol)) {
                continue;
            }

            Piece targetPiece = board.getTile(targetRow, targetCol).getPiece();
            if (targetPiece != null && targetPiece.getColor() != color) {
                moves.add(new Move(position, new Position(targetRow, targetCol)));
            }
        }
    }

    private boolean isInsideBoard(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}
