package it.cardgames.ui;

import it.cardgames.uno.UnoView;
import it.cardgames.briscola.BriscolaView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuView extends VBox {

    public MenuView(Stage stage) {
        setSpacing(20); setAlignment(Pos.CENTER);

        Button unoBtn = new Button("UNO");
        Button briscolaBtn = new Button("Briscola");

        unoBtn.setOnAction(e -> stage.setScene(new Scene(new UnoView(), 900, 600)));
        briscolaBtn.setOnAction(e -> stage.setScene(new Scene(new BriscolaView(), 900, 600)));

        getChildren().addAll(unoBtn, briscolaBtn);
    }
}
