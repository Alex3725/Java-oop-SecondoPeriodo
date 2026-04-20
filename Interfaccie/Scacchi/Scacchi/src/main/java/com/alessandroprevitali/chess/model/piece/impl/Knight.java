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

    // Tutti i salti possibili del cavallo.
    private static final int[][] KNIGHT_JUMPS = {
        {-2, -1},
        {-2, 1},
        {-1, -2},
        {-1, 2},
        {1, -2},
        {1, 2},
        {2, -1},
        {2, 1}
    };

    public Knight(Color color, Position position) {
        super(color, position);
    }

    /**
     * Restituisce il tipo del pezzo.
     *
     * @return PieceType.KNIGHT
     */
    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }

    /**
     * Calcola le pseudo-mosse del cavallo su tutti i salti a L.
     *
     * @param board board corrente
     * @return lista di pseudo-mosse geometricamente valide
     */
    @Override
    public List<Move> getPseudoLegalMoves(Board board) {
        List<Move> moves = new ArrayList<>();

        // Il cavallo prova tutti i salti a L.
        for (int[] jump : KNIGHT_JUMPS) {
            tryAddKnightMove(board, moves, jump[0], jump[1]);
        }

        return moves;
    }

    /**
     * Prova ad aggiungere una singola destinazione a L del cavallo se la casella è valida.
     *
     * @param board board corrente
     * @param moves collezione da arricchire con la mossa, se valida
     * @param rowStep incremento di riga del salto
     * @param colStep incremento di colonna del salto
     */
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

    /**
     * Verifica se una coordinata appartiene ai limiti della board.
     *
     * @param row riga da verificare
     * @param col colonna da verificare
     * @return true se la coordinata è dentro 0..7, false altrimenti
     */
    private boolean isInsideBoard(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}
