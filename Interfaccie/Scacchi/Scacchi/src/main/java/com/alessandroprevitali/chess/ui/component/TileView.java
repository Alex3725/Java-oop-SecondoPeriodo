package main.java.com.alessandroprevitali.chess.ui.component;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.java.com.alessandroprevitali.chess.model.piece.Piece;

public class TileView extends StackPane {

    private static final double TILE = 76;
    // Palette base e palette di evidenziazione della casella.
    private static final Color LIGHT        = Color.web("#F0D9B5");
    private static final Color DARK         = Color.web("#B58863");
    private static final Color SEL_LIGHT    = Color.web("#F6F669");
    private static final Color SEL_DARK     = Color.web("#CDD26E");
    private static final Color HINT_COLOR   = Color.web("#1A1A1A", 0.28);
    private static final Color CAPTURE_COLOR= Color.web("#1A1A1A", 0.22);

    private final Rectangle bg;
    private final Text pieceLabel;
    private final Circle legalDot;
    private final Circle captureDot;
    private final boolean light;

    public TileView(boolean isLight) {
        this.light = isLight;
        setPrefSize(TILE, TILE);
        setAlignment(Pos.CENTER);

        // Sfondo della casella.
        bg = new Rectangle(TILE, TILE);
        bg.setFill(isLight ? LIGHT : DARK);

        // Cerchio piccolo per suggerire una mossa libera.
        legalDot = new Circle(13, HINT_COLOR);
        legalDot.setVisible(false);

        // Anello per indicare una cattura.
        captureDot = new Circle(TILE/2 - 4, Color.TRANSPARENT);
        captureDot.setStroke(CAPTURE_COLOR);
        captureDot.setStrokeWidth(7);
        captureDot.setVisible(false);

        // Testo del pezzo: usa simboli Unicode per semplicità.
        pieceLabel = new Text();
        pieceLabel.setFont(Font.font("Segoe UI Symbol", 46));
        pieceLabel.setMouseTransparent(true);

        getChildren().addAll(bg, captureDot, legalDot, pieceLabel);
    }

    /**
     * Aggiorna l'aspetto della casella in base a pezzo, selezione e indicatori di mossa.
     *
     * @param piece pezzo presente sulla casella (null se vuota)
     * @param selected true se la casella è selezionata
     * @param legalMove true se la casella è una destinazione legale
     * @param isCapture true se la destinazione legale contiene un pezzo catturabile
     */
    public void update(Piece piece, boolean selected, boolean legalMove, boolean isCapture) {
        // Colore della casella in base al suo stato visivo.
        if (selected)       bg.setFill(light ? SEL_LIGHT : SEL_DARK);
        else if (isCapture) bg.setFill(light ? SEL_LIGHT.deriveColor(0,1,0.95,1) : SEL_DARK.deriveColor(0,1,0.95,1));
        else                bg.setFill(light ? LIGHT : DARK);

        // Simbolo del pezzo, se presente.
        if (piece != null) {
            pieceLabel.setText(piece.getSymbol());
            // Piccolo contorno per aumentare il contrasto dei pezzi chiari.
            if (piece.getColor() == main.java.com.alessandroprevitali.chess.model.piece.Color.WHITE) {
                pieceLabel.setFill(Color.web("#FEFEFE"));
                pieceLabel.setStroke(Color.web("#888888"));
                pieceLabel.setStrokeWidth(0.5);
            } else {
                pieceLabel.setFill(Color.web("#1A1A1A"));
                pieceLabel.setStroke(null);
            }
        } else {
            pieceLabel.setText("");
        }

        // Indicatori visivi delle mosse disponibili.
        boolean showDot     = legalMove && piece == null;
        boolean showCapture = legalMove && piece != null;
        legalDot.setVisible(showDot);
        captureDot.setVisible(showCapture);
    }

    /**
     * Esegue un flash rosso temporaneo per evidenziare il re sotto scacco.
     */
    public void flash() {
        // Breve evidenziazione rossa per segnalare il re sotto attacco.
        bg.setFill(Color.web("#FF5555", 0.5));

        javafx.animation.Timeline timeline = new javafx.animation.Timeline(
                new javafx.animation.KeyFrame(
                        javafx.util.Duration.millis(200),
                        e -> bg.setFill(light ? LIGHT : DARK)
                )
        );

        timeline.play();
    }

}
