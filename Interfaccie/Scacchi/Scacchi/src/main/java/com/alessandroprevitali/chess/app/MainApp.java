package main.java.com.alessandroprevitali.chess.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/alessandroprevitali/chess/ui/view/GameView.fxml")
        );

        Scene scene = new Scene(loader.load());
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.show();
    }
}