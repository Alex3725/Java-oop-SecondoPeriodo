package main.java.com.alessandroprevitali.chess.ui.controller;


import main.java.com.alessandroprevitali.chess.model.game.Game;
import main.java.com.alessandroprevitali.chess.service.GameService;
import main.java.com.alessandroprevitali.chess.ui.component.ChessBoardView;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class GameController {

    @FXML
    private BorderPane root;

    private Game game;
    private GameService gameService;

    @FXML
    public void initialize() {
        game = new Game();
        gameService = new GameService(game);

        ChessBoardView boardView = new ChessBoardView(gameService);
        root.setCenter(boardView);
    }
}