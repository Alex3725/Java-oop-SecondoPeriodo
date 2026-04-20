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

    // Direzioni ortogonali: destra, sinistra, basso, alto.
    private static final int[][] LINE_DIRECTIONS = {
        {0, 1},
        {0, -1},
        {1, 0},
        {-1, 0}
    };

    public Rook(Color color, Position position) {
        super(color, position);
    }

    /**
     * Restituisce il tipo del pezzo.
     *
     * @return PieceType.ROOK
     */
    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }

    /**
     * Calcola le pseudo-mosse della torre in tutte le direzioni ortogonali.
     *
     * @param board board corrente
     * @return lista di pseudo-mosse geometricamente valide
     */
    @Override
    public List<Move> getPseudoLegalMoves(Board board) {
        List<Move> moves = new ArrayList<>();

        // La torre può muoversi in tutte le direzioni ortogonali.
        for (int[] direction : LINE_DIRECTIONS) {
            addLineMoves(board, moves, direction[0], direction[1]);
        }

        return moves;
    }

    /**
     * Aggiunge tutte le mosse lineari della torre in una direzione finché non incontra un ostacolo.
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
