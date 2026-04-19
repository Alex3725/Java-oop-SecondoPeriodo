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
    private final TileView[][] tiles = new TileView[8][8];
    private Position selectedPos = null;
    private List<Position> legalTargets = List.of();
    private Runnable onMoveCallback;

    public ChessBoardView(GameService gameService, Runnable onMove) {
        this.gameService = gameService;
        this.onMoveCallback = onMove;
        setAlignment(Pos.CENTER);

        GridPane grid = new GridPane();
        grid.setHgap(0); grid.setVgap(0);

        // Etichette righe (8..1) a sinistra
        for (int r = 0; r < 8; r++) {
            Text lbl = makeCoordLabel(String.valueOf(8 - r));
            grid.add(lbl, 0, r);
        }

        // Caselle
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

        // Etichette colonne (a..h) in basso
        for (int c = 0; c < 8; c++) {
            Text lbl = makeCoordLabel(String.valueOf((char)('a' + c)));
            lbl.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
            GridPane.setHalignment(lbl, javafx.geometry.HPos.CENTER);
            grid.add(lbl, c + 1, 8);
        }

        getChildren().add(grid);
        refresh();
    }

    private Text makeCoordLabel(String t) {
        Text lbl = new Text(t);
        lbl.setFont(Font.font("Georgia", FontWeight.BOLD, 13));
        lbl.setFill(Color.web("#A08060"));
        GridPane.setHalignment(lbl, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(lbl, javafx.geometry.VPos.CENTER);
        javafx.scene.layout.GridPane.setMargin(lbl, new javafx.geometry.Insets(0,4,0,4));
        return lbl;
    }

    private void handleClick(int row, int col) {
        if (gameService.isGameOver()) return;
        Board board = gameService.getGame().getBoard();
        Piece piece = board.getTile(row, col).getPiece();
        main.java.com.alessandroprevitali.chess.model.piece.Color turn =
            gameService.getGame().getTurnManager().isWhiteTurn()
                ? main.java.com.alessandroprevitali.chess.model.piece.Color.WHITE
                : main.java.com.alessandroprevitali.chess.model.piece.Color.BLACK;

        if (selectedPos == null) {
            if (piece != null && piece.getColor() == turn) {
                selectedPos = new Position(row, col);
                legalTargets = gameService.getLegalMoves(piece)
                    .stream().map(Move::getTo).collect(Collectors.toList());
                refresh();
            }
        } else {
            Position target = new Position(row, col);
            if (isLegal(target)) {
                Move move = new Move(selectedPos, target);
                gameService.handleMove(move);
                selectedPos = null; legalTargets = List.of();
                refresh();
                if (onMoveCallback != null) onMoveCallback.run();
            } else if (piece != null && piece.getColor() == turn) {
                // Ri-seleziona altro pezzo
                selectedPos = new Position(row, col);
                legalTargets = gameService.getLegalMoves(piece)
                    .stream().map(Move::getTo).collect(Collectors.toList());
                refresh();
            } else {
                selectedPos = null; legalTargets = List.of();
                refresh();
            }
        }
    }

    private boolean isLegal(Position p) {
        return legalTargets.stream().anyMatch(t -> t.equals(p));
    }

    public void refresh() {
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
        // Se sotto scacco, lampeggia il re
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

    public void resetSelection() { selectedPos = null; legalTargets = List.of(); refresh(); }
}
