package main.java.com.alessandroprevitali.chess.ui.component;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.java.com.alessandroprevitali.chess.model.board.Board;
import main.java.com.alessandroprevitali.chess.model.board.Position;
import main.java.com.alessandroprevitali.chess.model.game.GameState;
import main.java.com.alessandroprevitali.chess.model.move.Move;
import main.java.com.alessandroprevitali.chess.model.piece.Piece;
import main.java.com.alessandroprevitali.chess.service.GameService;

import java.util.List;
import java.util.stream.Collectors;

public class ChessBoardView extends VBox {

    private final GameService gameService;
    // Cache visiva delle 64 caselle: evita di ricreare i nodi ad ogni refresh.
    private final TileView[][] tiles = new TileView[8][8];
    private Position selectedPos = null;
    private List<Position> legalTargets = List.of();
    private Runnable onMoveCallback;

    /**
     * Costruisce la view della scacchiera e registra il callback da eseguire dopo una mossa valida.
     *
     * @param gameService servizio applicativo usato per leggere e aggiornare lo stato della partita
     * @param onMove callback invocato dopo una mossa accettata per aggiornare elementi UI esterni
     */
    public ChessBoardView(GameService gameService, Runnable onMove) {
        this.gameService = gameService;
        this.onMoveCallback = onMove;
        setAlignment(Pos.CENTER);

        // Griglia principale della scacchiera con coordinate laterali.
        GridPane grid = new GridPane();
        grid.setHgap(0); grid.setVgap(0);

        // Etichette righe (8..1) a sinistra.
        for (int r = 0; r < 8; r++) {
            Text lbl = makeCoordLabel(String.valueOf(8 - r));
            grid.add(lbl, 0, r);
        }

        // Creazione di tutte le caselle interattive.
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                boolean isLight = (r + c) % 2 == 0;
                TileView tv = new TileView(isLight);
                final int fr = r, fc = c;
                tv.setOnMouseClicked(e -> handleClick(fr, fc));
                tiles[r][c] = tv;
                grid.add(tv, c + 1, r);
            }
        }

        // Etichette colonne (a..h) in basso.
        for (int c = 0; c < 8; c++) {
            Text lbl = makeCoordLabel(String.valueOf((char)('a' + c)));
            lbl.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
            GridPane.setHalignment(lbl, javafx.geometry.HPos.CENTER);
            grid.add(lbl, c + 1, 8);
        }

        getChildren().add(grid);
        refresh();
    }

    /**
     * Crea una label grafica per coordinate di riga o colonna.
     *
     * @param t testo della coordinata da visualizzare
     * @return nodo Text già stilizzato e pronto per essere inserito nella griglia
     */
    private Text makeCoordLabel(String t) {
        // Styling uniforme delle coordinate.
        Text lbl = new Text(t);
        lbl.setFont(Font.font("Georgia", FontWeight.BOLD, 13));
        lbl.setFill(Color.web("#A08060"));
        GridPane.setHalignment(lbl, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(lbl, javafx.geometry.VPos.CENTER);
        javafx.scene.layout.GridPane.setMargin(lbl, new javafx.geometry.Insets(0,4,0,4));
        return lbl;
    }

    /**
     * Gestisce il click utente con logica a due fasi: selezione pezzo e conferma destinazione.
     *
     * @param row riga cliccata
     * @param col colonna cliccata
     */
    private void handleClick(int row, int col) {
        // Due click: primo per selezionare, secondo per confermare la destinazione.
        if (gameService.isGameOver()) return;
        Board board = gameService.getGame().getBoard();
        Piece piece = board.getTile(row, col).getPiece();
        main.java.com.alessandroprevitali.chess.model.piece.Color turn =
            gameService.getGame().getTurnManager().isWhiteTurn()
                ? main.java.com.alessandroprevitali.chess.model.piece.Color.WHITE
                : main.java.com.alessandroprevitali.chess.model.piece.Color.BLACK;

        if (selectedPos == null) {
            if (piece != null && piece.getColor() == turn) {
                // Selezione iniziale del pezzo.
                selectedPos = new Position(row, col);
                legalTargets = gameService.getLegalMoves(piece)
                    .stream().map(Move::getTo).collect(Collectors.toList());
                refresh();
            }
        } else {
            Position target = new Position(row, col);
            if (isLegal(target)) {
                // Destinazione valida: invio la mossa al service.
                Move move = new Move(selectedPos, target);
                gameService.handleMove(move);
                selectedPos = null; legalTargets = List.of();
                refresh();
                if (onMoveCallback != null) onMoveCallback.run();
            } else if (piece != null && piece.getColor() == turn) {
                // Cambio selezione su un altro pezzo dello stesso colore.
                selectedPos = new Position(row, col);
                legalTargets = gameService.getLegalMoves(piece)
                    .stream().map(Move::getTo).collect(Collectors.toList());
                refresh();
            } else {
                // Click non valido: annullo la selezione.
                selectedPos = null; legalTargets = List.of();
                refresh();
            }
        }
    }

    /**
     * Verifica se una posizione è tra le destinazioni legali correnti.
     *
     * @param p posizione di destinazione da verificare
     * @return true se la posizione è raggiungibile dal pezzo selezionato, altrimenti false
     */
    private boolean isLegal(Position p) {
        // Un target è legale se presente tra le mosse calcolate per il pezzo selezionato.
        return legalTargets.stream().anyMatch(t -> t.equals(p));
    }

    /**
     * Ridisegna tutte le caselle in base allo stato della partita, alla selezione e alle mosse legali.
     */
    public void refresh() {
        // Ridisegna tutta la board in base allo stato corrente del gioco.
        Board board = gameService.getGame().getBoard();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece piece = board.getTile(r, c).getPiece();
                boolean sel  = selectedPos != null && selectedPos.equals(new Position(r, c));
                boolean legal = isLegal(new Position(r, c));
                boolean isCapture = legal && piece != null;
                tiles[r][c].update(piece, sel, legal, isCapture);
            }
        }
        // Se la partita è in scacco o matto, evidenzio il re minacciato.
        if (gameService.getGame().getState() == GameState.CHECK ||
            gameService.getGame().getState() == GameState.CHECKMATE) {
            main.java.com.alessandroprevitali.chess.model.piece.Color turn =
                gameService.getGame().getTurnManager().isWhiteTurn()
                    ? main.java.com.alessandroprevitali.chess.model.piece.Color.WHITE
                    : main.java.com.alessandroprevitali.chess.model.piece.Color.BLACK;
            Position kp = main.java.com.alessandroprevitali.chess.model.rules.CheckDetector
                .findKing(turn, board);
            if (kp != null) tiles[kp.getRow()][kp.getCol()].flash();
        }
    }

    /**
     * Azzera selezione e target legali, quindi aggiorna la scacchiera.
     */
    public void resetSelection() {
        // Pulisce stato locale della selezione quando si avvia una nuova partita.
        selectedPos = null;
        legalTargets = List.of();
        refresh();
    }
}
