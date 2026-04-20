package main.java.com.alessandroprevitali.chess.model.game;

import main.java.com.alessandroprevitali.chess.model.board.Board;

public class Game {

    // Stato corrente della partita: board, turno e risultato globale.
    private Board board;
    private TurnManager turnManager;
    private GameState state;

    public Game() {
        // Alla creazione parto sempre da una partita nuova.
        reset();
    }

    /**
     * Reinizializza completamente la partita con board e turno iniziali.
     */
    public void reset() {
        // Ricrea completamente board, turno e stato iniziale.
        board = new Board();
        turnManager = new TurnManager();
        state = GameState.ONGOING;
    }

    /**
    * Restituisce la board corrente della partita.
    *
    * @return board attuale
    */
    public Board getBoard() {
        return board;
    }

    /**
    * Restituisce il gestore del turno corrente.
    *
    * @return istanza di TurnManager
    */
    public TurnManager getTurnManager() {
        return turnManager;
    }

    /**
    * Restituisce lo stato globale della partita.
    *
    * @return stato corrente (ONGOING, CHECK, CHECKMATE, STALEMATE)
    */
    public GameState getState() {
        return state;
    }

    /**
     * Imposta lo stato globale della partita.
     *
     * @param state nuovo stato da applicare
     */
    public void setState(GameState state) {
        this.state = state;
    }
}
