package it.cardgames;

import it.cardgames.ui.MenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Card Games");
        stage.setScene(new Scene(new MenuView(stage), 900, 600));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
