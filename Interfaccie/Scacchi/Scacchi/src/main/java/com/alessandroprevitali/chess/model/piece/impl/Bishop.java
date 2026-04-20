package main.java.com.alessandroprevitali.chess.model.piece.impl;
import main.java.com.alessandroprevitali.chess.model.board.Board;
import main.java.com.alessandroprevitali.chess.model.board.Position;
import main.java.com.alessandroprevitali.chess.model.move.Move;
import main.java.com.alessandroprevitali.chess.model.piece.Color;
import main.java.com.alessandroprevitali.chess.model.piece.Piece;
import main.java.com.alessandroprevitali.chess.model.piece.PieceType;
import java.util.ArrayList;
import java.util.List;
public class Bishop extends Piece {

    // Direzioni diagonali dell'alfiere.
    private static final int[][] DIAGONAL_DIRECTIONS = {
        {1, 1},
        {1, -1},
        {-1, 1},
        {-1, -1}
    };

    public Bishop(Color color, Position position) {
        super(color, position);
    }

    /**
     * Restituisce il tipo del pezzo.
     *
     * @return PieceType.BISHOP
     */
    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }

    /**
     * Calcola le pseudo-mosse dell'alfiere nelle quattro diagonali.
     *
     * @param board board corrente
     * @return lista di pseudo-mosse geometricamente valide
     */
    @Override
    public List<Move> getPseudoLegalMoves(Board board) {
        List<Move> moves = new ArrayList<>();

        // L'alfiere si muove solo sulle diagonali.
        for (int[] direction : DIAGONAL_DIRECTIONS) {
            addLineMoves(board, moves, direction[0], direction[1]);
        }

        return moves;
    }

    /**
     * Aggiunge tutte le mosse diagonali dell'alfiere in una direzione fino al primo blocco.
     *
     * @param board board corrente
     * @param moves collezione da arricchire con le mosse trovate
     * @param rowStep incremento di riga per la direzione
     * @param colStep incremento di colonna per la direzione
     */
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
