package main.java.com.alessandroprevitali.chess.model.game;


import main.java.com.alessandroprevitali.chess.model.board.Board;

public class Game {

    private Board board;
    private GameState state;
    private TurnManager turnManager;

    public Game() {
        board = new Board();
        turnManager = new TurnManager();
        state = GameState.ONGOING;
    }

    public Board getBoard() { return board; }
    public TurnManager getTurnManager() { return turnManager; }
}