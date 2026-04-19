package main.java.com.alessandroprevitali.chess.model.piece.impl;
import main.java.com.alessandroprevitali.chess.model.board.Board;
import main.java.com.alessandroprevitali.chess.model.board.Position;
import main.java.com.alessandroprevitali.chess.model.move.Move;
import main.java.com.alessandroprevitali.chess.model.piece.Color;
import main.java.com.alessandroprevitali.chess.model.piece.Piece;
import main.java.com.alessandroprevitali.chess.model.piece.PieceType;
import java.util.ArrayList;
import java.util.List;
public class King extends Piece {
    public King(Color color, Position position) {
        super(color, position);
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }

    @Override
    public List<Move> getPseudoLegalMoves(Board board) {
        List<Move> moves = new ArrayList<>();

        addSingleStepMoves(board, moves);
        addCastlingMoves(board, moves);

        return moves;
    }

    private void addSingleStepMoves(Board board, List<Move> moves) {
        for (int rowStep = -1; rowStep <= 1; rowStep++) {
            for (int colStep = -1; colStep <= 1; colStep++) {
                if (rowStep == 0 && colStep == 0) {
                    continue;
                }

                int row = position.getRow() + rowStep;
                int col = position.getCol() + colStep;
                if (!isInsideBoard(row, col)) {
                    continue;
                }

                Piece target = board.getTile(row, col).getPiece();
                if (target == null || target.getColor() != color) {
                    moves.add(new Move(position, new Position(row, col)));
                }
            }
        }
    }

    private void addCastlingMoves(Board board, List<Move> moves) {
        if (hasMoved) {
            return;
        }

        int row = position.getRow();

        Piece kingsideRook = board.getTile(row, 7).getPiece();
        boolean canCastleKingside = kingsideRook != null
            && kingsideRook.getType() == PieceType.ROOK
            && kingsideRook.getColor() == color
            && !kingsideRook.hasMoved()
            && board.getTile(row, 5).getPiece() == null
            && board.getTile(row, 6).getPiece() == null;
        if (canCastleKingside) {
            moves.add(new Move(position, new Position(row, 6)));
        }

        Piece queensideRook = board.getTile(row, 0).getPiece();
        boolean canCastleQueenside = queensideRook != null
            && queensideRook.getType() == PieceType.ROOK
            && queensideRook.getColor() == color
            && !queensideRook.hasMoved()
            && board.getTile(row, 1).getPiece() == null
            && board.getTile(row, 2).getPiece() == null
            && board.getTile(row, 3).getPiece() == null;
        if (canCastleQueenside) {
            moves.add(new Move(position, new Position(row, 2)));
        }
    }

    private boolean isInsideBoard(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}
