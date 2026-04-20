package main.java.com.alessandroprevitali.chess.service;

import main.java.com.alessandroprevitali.chess.model.board.Board;
import main.java.com.alessandroprevitali.chess.model.board.Position;
import main.java.com.alessandroprevitali.chess.model.board.Tile;
import main.java.com.alessandroprevitali.chess.model.game.Game;
import main.java.com.alessandroprevitali.chess.model.game.GameState;
import main.java.com.alessandroprevitali.chess.model.move.Move;
import main.java.com.alessandroprevitali.chess.model.piece.Color;
import main.java.com.alessandroprevitali.chess.model.piece.Piece;
import main.java.com.alessandroprevitali.chess.model.piece.PieceType;
import main.java.com.alessandroprevitali.chess.model.piece.impl.Queen;
import main.java.com.alessandroprevitali.chess.model.rules.CheckDetector;

import java.util.List;

public class GameService {

    private Game game;
    private MoveService moveService;

    public GameService() {
        // Il service coordina un'istanza di partita e la logica di validazione delle mosse.
        this.game = new Game();
        this.moveService = new MoveService();
    }

    /**
     * Espone l'istanza corrente di partita usata dal service.
     *
     * @return oggetto Game attualmente gestito
     */
    public Game getGame() {
        return game;
    }

    /**
     * Tenta di applicare una mossa completa: validazione, esecuzione, cambio turno e aggiornamento stato.
     *
     * @param move mossa richiesta dalla UI
     * @return true se la mossa è valida ed eseguita, false in caso contrario
     */
    public boolean handleMove(Move move) {
        // Blocca mosse quando la partita è già terminata.
        if (game.getState() == GameState.CHECKMATE || game.getState() == GameState.STALEMATE) {
            return false;
        }

        // La validazione completa è demandata a MoveService.
        if (!moveService.isValid(move, game)) {
            return false;
        }

        // Applica la mossa, cambia il turno e aggiorna il risultato della partita.
        applyMove(move);
        game.getTurnManager().switchTurn();
        updateGameState();
        return true;
    }

    /**
     * Applica fisicamente una mossa sulla board, includendo arrocco e promozione pedone.
     *
     * @param move mossa già validata da eseguire
     */
    private void applyMove(Move move) {
        // Spostamento fisico dei pezzi sulla board.
        Board board = game.getBoard();
        Tile from = board.getTile(move.getFrom().getRow(), move.getFrom().getCol());
        Tile to = board.getTile(move.getTo().getRow(), move.getTo().getCol());
        Piece piece = from.getPiece();

        // Riconoscimento dell'arrocco in base allo spostamento orizzontale del re.
        boolean isCastleKingside = piece.getType() == PieceType.KING
            && move.getTo().getCol() - move.getFrom().getCol() == 2;
        boolean isCastleQueenside = piece.getType() == PieceType.KING
            && move.getFrom().getCol() - move.getTo().getCol() == 2;

        // Aggiorno il pezzo principale.
        from.setPiece(null);
        to.setPiece(piece);
        piece.setPosition(move.getTo());
        piece.setHasMoved(true);

        // Se c'è arrocco, sposto anche la torre.
        if (isCastleKingside) {
            Tile rFrom = board.getTile(move.getFrom().getRow(), 7);
            Tile rTo = board.getTile(move.getFrom().getRow(), 5);
            Piece rook = rFrom.getPiece();

            rFrom.setPiece(null);
            rTo.setPiece(rook);

            if (rook != null) {
                rook.setPosition(new Position(move.getFrom().getRow(), 5));
                rook.setHasMoved(true);
            }
        } else if (isCastleQueenside) {
            Tile rFrom = board.getTile(move.getFrom().getRow(), 0);
            Tile rTo = board.getTile(move.getFrom().getRow(), 3);
            Piece rook = rFrom.getPiece();

            rFrom.setPiece(null);
            rTo.setPiece(rook);

            if (rook != null) {
                rook.setPosition(new Position(move.getFrom().getRow(), 3));
                rook.setHasMoved(true);
            }
        }

        // Promozione automatica del pedone arrivato in ultima traversa.
        if (piece.getType() == PieceType.PAWN) {
            int promotionRow = (piece.getColor() == Color.WHITE) ? 0 : 7;
            if (move.getTo().getRow() == promotionRow) {
                to.setPiece(new Queen(piece.getColor(), move.getTo()));
            }
        }
    }

    /**
     * Ricalcola lo stato globale della partita per il giocatore che deve muovere.
     */
    private void updateGameState() {
        // Dopo ogni mossa controllo se il giocatore avversario è sotto scacco e ha mosse disponibili.
        Color opponent = game.getTurnManager().isWhiteTurn() ? Color.WHITE : Color.BLACK;
        Board board = game.getBoard();
        boolean inCheck = CheckDetector.isInCheck(opponent, board);
        boolean hasLegal = moveService.hasAnyLegalMoves(opponent, board);
        if (!hasLegal) {
            game.setState(inCheck ? GameState.CHECKMATE : GameState.STALEMATE);
        } else {
            game.setState(inCheck ? GameState.CHECK : GameState.ONGOING);
        }
    }

    /**
     * Restituisce le mosse legali del pezzo richiesto nello stato corrente.
     *
     * @param piece pezzo selezionato
     * @return lista di mosse legali per il pezzo
     */
    public List<Move> getLegalMoves(Piece piece) {
        // La UI usa questo metodo per evidenziare le caselle raggiungibili dal pezzo selezionato.
        return moveService.getLegalMoves(piece, game.getBoard());
    }

    /**
     * Ripristina una nuova partita con board e turno iniziali.
     */
    public void reset() {
        // Reset completo della partita.
        game.reset();
    }

    /**
     * Indica se la partita è terminata.
     *
     * @return true se lo stato è CHECKMATE o STALEMATE, false altrimenti
     */
    public boolean isGameOver() {
        // La UI usa questo check per bloccare input dopo la fine della partita.
        GameState s = game.getState();
        return s == GameState.CHECKMATE || s == GameState.STALEMATE;
    }
}
