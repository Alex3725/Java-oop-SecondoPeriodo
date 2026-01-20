package it.cardgames.ui;

import it.cardgames.model.Carta;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CardView extends StackPane {

    public CardView(Carta carta, Color color) {
        Rectangle bg = new Rectangle(90, 130);
        bg.setArcHeight(15);
        bg.setArcWidth(15);
        bg.setFill(color);
        bg.setStroke(Color.BLACK);

        Text txt = new Text(carta.getNome());
        txt.setWrappingWidth(80);
        txt.setFill(Color.WHITE);

        getChildren().addAll(bg, txt);
        AnimationUtil.animateDraw(this);
    }
}
