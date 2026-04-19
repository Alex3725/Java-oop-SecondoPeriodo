package main.java.com.alessandroprevitali.chess.service;

import main.java.com.alessandroprevitali.chess.model.board.Board;
import main.java.com.alessandroprevitali.chess.model.board.Tile;
import main.java.com.alessandroprevitali.chess.model.board.Position;
import main.java.com.alessandroprevitali.chess.model.game.Game;
import main.java.com.alessandroprevitali.chess.model.move.Move;
import main.java.com.alessandroprevitali.chess.model.piece.Color;
import main.java.com.alessandroprevitali.chess.model.piece.Piece;
import main.java.com.alessandroprevitali.chess.model.piece.PieceType;
import main.java.com.alessandroprevitali.chess.model.rules.CheckDetector;

import java.util.ArrayList;
import java.util.List;

public class MoveService {

    public boolean isValid(Move move, Game game) {
        Board board = game.getBoard();
        Piece piece = board.getTile(move.getFrom().getRow(), move.getFrom().getCol()).getPiece();

        if (!isPiecePresentAndOnTurn(piece, game)) {
            return false;
        }

        if (!isPseudoLegalTarget(move, piece, board)) {
            return false;
        }

        if (isCastleMove(move, piece) && !isCastlingPathSafe(move, piece.getColor(), board)) {
            return false;
        }

        return !leavesKingInCheck(move, piece.getColor(), board);
    }

    public List<Move> getLegalMoves(Piece piece, Board board) {
        List<Move> legalMoves = new ArrayList<>();

        for (Move move : piece.getPseudoLegalMoves(board)) {
            boolean castleAllowed = !isCastleMove(move, piece) || isCastlingPathSafe(move, piece.getColor(), board);
            if (!castleAllowed) {
                continue;
            }

            if (!leavesKingInCheck(move, piece.getColor(), board)) {
                legalMoves.add(move);
            }
        }

        return legalMoves;
    }

    private boolean isPiecePresentAndOnTurn(Piece piece, Game game) {
        if (piece == null) {
            return false;
        }

        Color currentTurn = game.getTurnManager().isWhiteTurn() ? Color.WHITE : Color.BLACK;
        return piece.getColor() == currentTurn;
    }

    private boolean isPseudoLegalTarget(Move move, Piece piece, Board board) {
        for (Move pseudoMove : piece.getPseudoLegalMoves(board)) {
            if (pseudoMove.getTo().equals(move.getTo())) {
                return true;
            }
        }
        return false;
    }

    public boolean leavesKingInCheck(Move move, Color movingColor, Board board) {
        Tile from = board.getTile(move.getFrom().getRow(), move.getFrom().getCol());
        Tile to   = board.getTile(move.getTo().getRow(),   move.getTo().getCol());
        Piece moved    = from.getPiece();
        Piece captured = to.getPiece();
        Position origPos = moved.getPosition();

        // Simula la mossa
        from.setPiece(null);
        to.setPiece(moved);
        moved.setPosition(move.getTo());

        // Gestione arrocco: sposta anche la torre
        boolean isCastleKingside = moved.getType() == PieceType.KING
            && move.getTo().getCol() - move.getFrom().getCol() == 2;
        boolean isCastleQueenside = moved.getType() == PieceType.KING
            && move.getFrom().getCol() - move.getTo().getCol() == 2;

        Piece castleRook = null;
        Tile castleRookFrom = null;
        Tile castleRookTo = null;

        if (isCastleKingside) {
            castleRookFrom = board.getTile(move.getFrom().getRow(), 7);
            castleRookTo = board.getTile(move.getFrom().getRow(), 5);
            castleRook = castleRookFrom.getPiece();

            castleRookFrom.setPiece(null);
            castleRookTo.setPiece(castleRook);

            if (castleRook != null) {
                castleRook.setPosition(new Position(move.getFrom().getRow(), 5));
            }
        } else if (isCastleQueenside) {
            castleRookFrom = board.getTile(move.getFrom().getRow(), 0);
            castleRookTo = board.getTile(move.getFrom().getRow(), 3);
            castleRook = castleRookFrom.getPiece();

            castleRookFrom.setPiece(null);
            castleRookTo.setPiece(castleRook);

            if (castleRook != null) {
                castleRook.setPosition(new Position(move.getFrom().getRow(), 3));
            }
        }

        boolean inCheck = CheckDetector.isInCheck(movingColor, board);

        // Annulla la mossa
        from.setPiece(moved);
        to.setPiece(captured);
        moved.setPosition(origPos);
        if (isCastleKingside && castleRook != null) {
            castleRookTo.setPiece(null);
            castleRookFrom.setPiece(castleRook);
            castleRook.setPosition(new Position(move.getFrom().getRow(), 7));
        } else if (isCastleQueenside && castleRook != null) {
            castleRookTo.setPiece(null);
            castleRookFrom.setPiece(castleRook);
            castleRook.setPosition(new Position(move.getFrom().getRow(), 0));
        }

        return inCheck;
    }

    public boolean hasAnyLegalMoves(Color color, Board board) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = board.getTile(r, c).getPiece();
                if (p != null && p.getColor() == color && !getLegalMoves(p, board).isEmpty()) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isCastleMove(Move move, Piece piece) {
        return piece.getType() == PieceType.KING
            && Math.abs(move.getTo().getCol() - move.getFrom().getCol()) == 2;
    }

    private boolean isCastlingPathSafe(Move move, Color movingColor, Board board) {
        // Regola 1: il re non puo arroccare se e' gia sotto scacco.
        if (CheckDetector.isInCheck(movingColor, board)) {
            return false;
        }

        int fromRow = move.getFrom().getRow();
        int fromCol = move.getFrom().getCol();
        int toCol = move.getTo().getCol();
        int step = (toCol > fromCol) ? 1 : -1;

        // Regola 2: la casella intermedia non deve essere attaccata.
        if (isKingInCheckAfterTemporaryMove(fromRow, fromCol, fromCol + step, movingColor, board)) {
            return false;
        }

        // Regola 3: anche la casella finale non deve essere attaccata.
        return !isKingInCheckAfterTemporaryMove(fromRow, fromCol, toCol, movingColor, board);
    }

    private boolean isKingInCheckAfterTemporaryMove(int row, int fromCol, int toCol, Color movingColor, Board board) {
        Tile from = board.getTile(row, fromCol);
        Tile to = board.getTile(row, toCol);
        Piece king = from.getPiece();
        Piece captured = to.getPiece();
        Position original = king.getPosition();

        from.setPiece(null);
        to.setPiece(king);
        king.setPosition(new Position(row, toCol));

        boolean inCheck = CheckDetector.isInCheck(movingColor, board);

        to.setPiece(captured);
        from.setPiece(king);
        king.setPosition(original);

        return inCheck;
    }
}
