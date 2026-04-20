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

    // Tutti gli spostamenti di una casella del re.
    private static final int[][] KING_STEPS = {
        {-1, -1},
        {-1, 0},
        {-1, 1},
        {0, -1},
        {0, 1},
        {1, -1},
        {1, 0},
        {1, 1}
    };

    public King(Color color, Position position) {
        super(color, position);
    }

    /**
     * Restituisce il tipo del pezzo.
     *
     * @return PieceType.KING
     */
    @Override
    public PieceType getType() {
        return PieceType.KING;
    }

    /**
     * Calcola le pseudo-mosse del re (mosse singole e possibili arrocchi geometrici).
     *
     * @param board board corrente
     * @return lista di pseudo-mosse geometricamente valide
     */
    @Override
    public List<Move> getPseudoLegalMoves(Board board) {
        List<Move> moves = new ArrayList<>();

        // Il re può muoversi di una casella e, se consentito, arroccare.
        addSingleStepMoves(board, moves);
        addCastlingMoves(board, moves);

        return moves;
    }

    /**
     * Aggiunge tutte le mosse standard del re di una casella nelle otto direzioni.
     *
     * @param board board corrente
     * @param moves collezione da arricchire con le mosse trovate
     */
    private void addSingleStepMoves(Board board, List<Move> moves) {
        for (int[] step : KING_STEPS) {
            int row = position.getRow() + step[0];
            int col = position.getCol() + step[1];

            if (!isInsideBoard(row, col)) {
                continue;
            }

            Piece target = board.getTile(row, col).getPiece();
            if (target == null || target.getColor() != color) {
                moves.add(new Move(position, new Position(row, col)));
            }
        }
    }

    /**
     * Aggiunge eventuali mosse di arrocco valide dal punto di vista geometrico.
     *
     * @param board board corrente
     * @param moves collezione da arricchire con le mosse di arrocco possibili
     */
    private void addCastlingMoves(Board board, List<Move> moves) {
        if (hasMoved) {
            return;
        }

        int row = position.getRow();

        // Configurazioni possibili per arrocco corto e lungo.
        int[][] castlingTargets = {
            {7, 5, 6},
            {0, 1, 2, 3}
        };

        for (int[] castling : castlingTargets) {
            int rookCol = castling[0];
            int targetCol = (rookCol == 7) ? 6 : 2;
            Piece rook = board.getTile(row, rookCol).getPiece();

            boolean canCastle = rook != null
                && rook.getType() == PieceType.ROOK
                && rook.getColor() == color
                && !rook.hasMoved();

            for (int index = 1; canCastle && index < castling.length; index++) {
                if (board.getTile(row, castling[index]).getPiece() != null) {
                    canCastle = false;
                }
            }

            if (canCastle) {
                moves.add(new Move(position, new Position(row, targetCol)));
            }
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
