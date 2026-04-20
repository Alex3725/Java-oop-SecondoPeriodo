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

    /**
     * Verifica se una mossa è valida rispetto a turno, movimento del pezzo, arrocco e sicurezza del re.
     *
     * @param move mossa da verificare
     * @param game partita corrente
     * @return true se la mossa è valida, false altrimenti
     */
    public boolean isValid(Move move, Game game) {
        // Validazione in cascata: turno, geometria, arrocco sicuro, re non esposto.
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

    /**
     * Calcola le mosse legali filtrando le pseudo-mosse che lasciano il re in scacco.
     *
     * @param piece pezzo di cui calcolare le mosse
     * @param board board su cui calcolare le mosse
     * @return lista di mosse realmente legali
     */
    public List<Move> getLegalMoves(Piece piece, Board board) {
        // Trasforma le pseudo-mosse del pezzo in mosse realmente legali.
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

    /**
     * Controlla che il pezzo esista e appartenga al giocatore di turno.
     *
     * @param piece pezzo in casella sorgente
     * @param game partita corrente
     * @return true se il pezzo può essere mosso nel turno corrente, false altrimenti
     */
    private boolean isPiecePresentAndOnTurn(Piece piece, Game game) {
        // Non si può muovere una casella vuota o un pezzo del colore sbagliato.
        if (piece == null) {
            return false;
        }

        Color currentTurn = game.getTurnManager().isWhiteTurn() ? Color.WHITE : Color.BLACK;
        return piece.getColor() == currentTurn;
    }

    /**
     * Verifica che la destinazione appartenga alle pseudo-mosse del pezzo.
     *
     * @param move mossa da verificare
     * @param piece pezzo da muovere
     * @param board board corrente
     * @return true se la destinazione è geometricamente valida, false altrimenti
     */
    private boolean isPseudoLegalTarget(Move move, Piece piece, Board board) {
        // Controlla solo la geometria del movimento del pezzo.
        for (Move pseudoMove : piece.getPseudoLegalMoves(board)) {
            if (pseudoMove.getTo().equals(move.getTo())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Simula e annulla una mossa per verificare se il re del colore che muove resta in scacco.
     *
     * @param move mossa da simulare
     * @param movingColor colore del giocatore che sta muovendo
     * @param board board corrente
     * @return true se dopo la simulazione il re resta in scacco, false altrimenti
     */
    public boolean leavesKingInCheck(Move move, Color movingColor, Board board) {
        // Simula la mossa, controlla lo scacco e poi ripristina tutto com'era.
        Tile from = board.getTile(move.getFrom().getRow(), move.getFrom().getCol());
        Tile to   = board.getTile(move.getTo().getRow(),   move.getTo().getCol());
        Piece moved    = from.getPiece();
        Piece captured = to.getPiece();
        Position origPos = moved.getPosition();

        // Spostamento temporaneo del pezzo.
        from.setPiece(null);
        to.setPiece(moved);
        moved.setPosition(move.getTo());

        // Se la mossa è un arrocco, simulo anche lo spostamento della torre.
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

        // Dopo la simulazione verifico se il re del colore mosso è in scacco.
        boolean inCheck = CheckDetector.isInCheck(movingColor, board);

        // Ripristino completo dello stato originale.
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

    /**
     * Controlla se il colore indicato ha almeno una mossa legale disponibile.
     *
     * @param color colore del giocatore da analizzare
     * @param board board corrente
     * @return true se esiste almeno una mossa legale, false altrimenti
     */
    public boolean hasAnyLegalMoves(Color color, Board board) {
        // Serve per distinguere tra scacco, matto e stallo.
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

    /**
     * Riconosce una mossa di arrocco dal pattern di spostamento del re.
     *
     * @param move mossa da analizzare
     * @param piece pezzo mosso
     * @return true se la mossa rappresenta un arrocco, false altrimenti
     */
    private boolean isCastleMove(Move move, Piece piece) {
        // L'arrocco è l'unico movimento del re lungo di due colonne.
        return piece.getType() == PieceType.KING
            && Math.abs(move.getTo().getCol() - move.getFrom().getCol()) == 2;
    }

    /**
     * Verifica che il percorso dell'arrocco non attraversi o termini su caselle attaccate.
     *
     * @param move mossa di arrocco da controllare
     * @param movingColor colore del re che arrocca
     * @param board board corrente
     * @return true se l'arrocco è sicuro, false altrimenti
     */
    private boolean isCastlingPathSafe(Move move, Color movingColor, Board board) {
        // Regola 1: il re non può arroccare se è già sotto scacco.
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

    /**
     * Sposta temporaneamente il re su una colonna specifica per verificare se risulta sotto attacco.
     *
     * @param row riga del re
     * @param fromCol colonna iniziale del re
     * @param toCol colonna da simulare
     * @param movingColor colore del re
     * @param board board corrente
     * @return true se il re risulterebbe in scacco nella posizione simulata, false altrimenti
     */
    private boolean isKingInCheckAfterTemporaryMove(int row, int fromCol, int toCol, Color movingColor, Board board) {
        // Variante ridotta del make/unmake usata dal controllo dell'arrocco.
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
