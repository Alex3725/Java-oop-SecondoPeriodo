package main.java.com.alessandroprevitali.chess.ui.component;


import main.java.com.alessandroprevitali.chess.service.GameService;
import javafx.scene.layout.GridPane;

public class ChessBoardView extends GridPane {

    public ChessBoardView(GameService gameService) {

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                add(new TileView(r, c, gameService), c, r);
            }
        }
    }
}