package main.java.com.alessandroprevitali.chess.service;

import main.java.com.alessandroprevitali.chess.model.game.Game;
import main.java.com.alessandroprevitali.chess.model.move.Move;


public class GameService {

    private Game game;
    private MoveService moveService;

    public GameService(Game game) {
        this.game = game;
        this.moveService = new MoveService();
    }

    public Game getGame() {
        return game;
    }

    public boolean handleMove(Move move) {

        if (!moveService.isValid(move, game)) {
            return false;
        }

        // TODO: aggiornerai board qui

        game.getTurnManager().switchTurn();
        return true;
    }
}