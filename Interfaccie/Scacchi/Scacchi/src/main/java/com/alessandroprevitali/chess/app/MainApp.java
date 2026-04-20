package main.java.com.alessandroprevitali.chess.app;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.java.com.alessandroprevitali.chess.model.game.GameState;
import main.java.com.alessandroprevitali.chess.service.GameService;
import main.java.com.alessandroprevitali.chess.ui.component.ChessBoardView;

public class MainApp extends Application {

    private GameService gameService;
    private ChessBoardView boardView;
    private Text statusText;
    private Text subText;

    /**
     * Avvia l'applicazione JavaFX.
     *
     * @param args argomenti da linea di comando
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Crea la finestra principale, collega UI e service e mostra la scena iniziale.
     *
     * @param stage stage JavaFX principale
     */
    @Override
    public void start(Stage stage) {
        // Il service contiene tutta la logica della partita.
        gameService = new GameService();

        // Layout principale con stile scuro ispirato a una scacchiera classica.
        BorderPane root = new BorderPane();
        root.setStyle(
            "-fx-background-color: linear-gradient(to bottom right, #1C1008, #2E1A0A);"
        );
        root.setPadding(new Insets(28));

        // Header con titolo e pulsante di reset.
        HBox header = new HBox(14);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(0, 0, 18, 8));

        Text logo = new Text("♔");
        logo.setFont(Font.font("Segoe UI Symbol", 38));
        logo.setFill(Color.web("#D4A947"));

        Text title = new Text("CHESS");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 28));
        title.setFill(Color.web("#E8D5A0"));
        title.setLineSpacing(6);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button newGameBtn = new Button("Nuova Partita");
        newGameBtn.setStyle(
            "-fx-background-color: #D4A947;" +
            "-fx-text-fill: #1C1008;" +
            "-fx-font-family: Georgia;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 13px;" +
            "-fx-padding: 8 18 8 18;" +
            "-fx-cursor: hand;" +
            "-fx-background-radius: 4;"
        );
        newGameBtn.setOnMouseEntered(e -> newGameBtn.setStyle(
            "-fx-background-color: #F0C060;" +
            "-fx-text-fill: #1C1008;" +
            "-fx-font-family: Georgia;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 13px;" +
            "-fx-padding: 8 18 8 18;" +
            "-fx-cursor: hand;" +
            "-fx-background-radius: 4;"
        ));
        newGameBtn.setOnMouseExited(e -> newGameBtn.setStyle(
            "-fx-background-color: #D4A947;" +
            "-fx-text-fill: #1C1008;" +
            "-fx-font-family: Georgia;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 13px;" +
            "-fx-padding: 8 18 8 18;" +
            "-fx-cursor: hand;" +
            "-fx-background-radius: 4;"
        ));
        newGameBtn.setOnAction(e -> resetGame());

        header.getChildren().addAll(logo, title, spacer, newGameBtn);

        // Pannello di stato: turno corrente, scacco, matto e stallo.
        VBox statusBox = new VBox(4);
        statusBox.setAlignment(Pos.CENTER);
        statusBox.setPadding(new Insets(14, 0, 14, 0));

        statusText = new Text("● Turno del Bianco");
        statusText.setFont(Font.font("Georgia", FontWeight.BOLD, 17));
        statusText.setFill(Color.web("#E8D5A0"));

        subText = new Text("Seleziona un pezzo per iniziare");
        subText.setFont(Font.font("Georgia", FontWeight.NORMAL, 12));
        subText.setFill(Color.web("#A08060"));

        statusBox.getChildren().addAll(statusText, subText);

        // Componente della board con ombra per dare profondità.
        boardView = new ChessBoardView(gameService, this::onMove);
        DropShadow shadow = new DropShadow(28, Color.web("#000000", 0.7));
        boardView.setEffect(shadow);

        // Centro della finestra: stato sopra e board sotto.
        VBox center = new VBox(0, statusBox, boardView);
        center.setAlignment(Pos.CENTER);

        root.setTop(header);
        root.setCenter(center);

        // Suggerimento rapido per l'utente.
        Text footer = new Text("Clicca un pezzo per selezionarlo, poi clicca la destinazione  •  Promozione automatica a Regina");
        footer.setFont(Font.font("Georgia", FontWeight.NORMAL, 10));
        footer.setFill(Color.web("#6B5040"));
        BorderPane.setAlignment(footer, Pos.CENTER);
        BorderPane.setMargin(footer, new Insets(14, 0, 0, 0));
        root.setBottom(footer);

        Scene scene = new Scene(root, 780, 860);
        stage.setTitle("♔ Chess");
        stage.setScene(scene);
        stage.setMinWidth(740);
        stage.setMinHeight(820);
        stage.setResizable(true);
        stage.show();

        updateStatus();
    }

    /**
     * Callback eseguito dopo una mossa valida per aggiornare lo stato visivo del turno.
     */
    private void onMove() {
        // Aggiorna il testo di stato e aggiunge un piccolo fade al cambio turno.
        updateStatus();
        FadeTransition ft = new FadeTransition(Duration.millis(180), statusText);
        ft.setFromValue(0.3); ft.setToValue(1.0); ft.play();
    }

    /**
     * Aggiorna testi e colori del pannello stato in base al GameState corrente.
     */
    private void updateStatus() {
        // Il testo dipende dallo stato globale della partita.
        GameState state = gameService.getGame().getState();
        boolean whiteTurn = gameService.getGame().getTurnManager().isWhiteTurn();

        switch (state) {
            case CHECKMATE -> {
                String winner = whiteTurn ? "Nero" : "Bianco";
                statusText.setText("♛ Scacco Matto! Vince il " + winner);
                statusText.setFill(Color.web("#FF6B6B"));
                subText.setText("Premi «Nuova Partita» per ricominciare");
            }
            case STALEMATE -> {
                statusText.setText("⬛ Stallo! Patta");
                statusText.setFill(Color.web("#AAAAAA"));
                subText.setText("Nessuna mossa disponibile");
            }
            case CHECK -> {
                String c = whiteTurn ? "Bianco" : "Nero";
                statusText.setText("⚠ Scacco al Re! — Turno del " + c);
                statusText.setFill(Color.web("#FFB347"));
                subText.setText("Il re è sotto attacco, deve difendersi");
            }
            default -> {
                String dot = whiteTurn ? "○" : "●";
                String c   = whiteTurn ? "Bianco" : "Nero";
                statusText.setText(dot + " Turno del " + c);
                statusText.setFill(Color.web("#E8D5A0"));
                subText.setText("Seleziona un pezzo per vedere le mosse disponibili");
            }
        }
    }

    /**
     * Ripristina una nuova partita e aggiorna interfaccia e animazioni di reset.
     */
    private void resetGame() {
        // Reset completo della partita e della UI.
        gameService.reset();
        boardView.resetSelection();
        boardView.refresh();
        updateStatus();
        FadeTransition ft = new FadeTransition(Duration.millis(300), boardView);
        ft.setFromValue(0.0); ft.setToValue(1.0); ft.play();
    }
}
