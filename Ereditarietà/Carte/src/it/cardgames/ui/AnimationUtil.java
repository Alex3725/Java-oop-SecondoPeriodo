package it.cardgames.ui;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class AnimationUtil {

    public static void animateDraw(Node card) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(300), card);
        tt.setFromY(-50);
        tt.setToY(0);
        tt.play();
    }
}
