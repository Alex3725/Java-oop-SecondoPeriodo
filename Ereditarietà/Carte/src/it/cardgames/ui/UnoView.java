package it.cardgames.ui;

import it.cardgames.uno.UnoGioco;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class UnoView extends BorderPane {

    private final UnoGioco gioco = new UnoGioco();
    private final Label info = new Label();

    public UnoView() {
        gioco.inizia();

        Button turnoBtn = new Button("Gioca Turno");
        turnoBtn.setOnAction(e -> {
            gioco.giocaTurno();
            info.setText(gioco.getStato());
        });

        setTop(info);
        HBox center = new HBox(turnoBtn);
        center.setAlignment(Pos.CENTER);
        setCenter(center);
    }
}
