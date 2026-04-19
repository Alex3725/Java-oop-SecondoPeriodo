package main.java.com.alessandroprevitali.chess.ui.component;

import main.java.com.alessandroprevitali.chess.model.board.Position;
import main.java.com.alessandroprevitali.chess.model.move.Move;
import main.java.com.alessandroprevitali.chess.service.GameService;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TileView extends StackPane {

    private static Position selected = null;

    public TileView(int row, int col, GameService gameService) {

        Rectangle bg = new Rectangle(80, 80);
        bg.setFill((row + col) % 2 == 0 ? Color.BEIGE : Color.BROWN);

        getChildren().add(bg);

        setOnMouseClicked(e -> {

            if (selected == null) {
                selected = new Position(row, col);
                System.out.println("Selezionato: " + row + "," + col);
            } else {
                Move move = new Move(selected, new Position(row, col));

                boolean success = gameService.handleMove(move);

                System.out.println("Move: " + success);

                selected = null;
            }
        });
    }
}