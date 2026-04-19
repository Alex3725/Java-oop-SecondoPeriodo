package main.java.com.alessandroprevitali.chess.model.board;

import main.java.com.alessandroprevitali.chess.model.piece.Color;
import main.java.com.alessandroprevitali.chess.model.piece.Piece;
import main.java.com.alessandroprevitali.chess.model.piece.impl.*;

public class Board {

    private Tile[][] tiles = new Tile[8][8];

    public Board() {
        init();
        initializePieces();
    }

    private void init() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                tiles[r][c] = new Tile(new Position(r, c));
            }
        }
    }

    private void initializePieces() {
        // Pezzi neri (riga 0)

        setPiece(0, 0, new Rook(Color.BLACK, new Position(0, 0)));
        setPiece(0, 1, new Knight(Color.BLACK, new Position(0, 1)));
        setPiece(0, 2, new Bishop(Color.BLACK, new Position(0, 2)));
        setPiece(0, 3, new Queen(Color.BLACK, new Position(0, 3)));
        setPiece(0, 4, new King(Color.BLACK, new Position(0, 4)));
        setPiece(0, 5, new Bishop(Color.BLACK, new Position(0, 5)));
        setPiece(0, 6, new Knight(Color.BLACK, new Position(0, 6)));
        setPiece(0, 7, new Rook(Color.BLACK, new Position(0, 7)));

        for (int c = 0; c < 8; c++) {
            setPiece(1, c, new Pawn(Color.BLACK, new Position(1, c)));
        }

        // Pedoni bianchi (riga 6) e pezzi bianchi (riga 7)

        for (int c = 0; c < 8; c++) {
            setPiece(6, c, new Pawn(Color.WHITE, new Position(6, c)));
        }
        setPiece(7, 0, new Rook(Color.WHITE, new Position(7, 0)));
        setPiece(7, 1, new Knight(Color.WHITE, new Position(7, 1)));
        setPiece(7, 2, new Bishop(Color.WHITE, new Position(7, 2)));
        setPiece(7, 3, new Queen(Color.WHITE, new Position(7, 3)));
        setPiece(7, 4, new King(Color.WHITE, new Position(7, 4)));
        setPiece(7, 5, new Bishop(Color.WHITE, new Position(7, 5)));
        setPiece(7, 6, new Knight(Color.WHITE, new Position(7, 6)));
        setPiece(7, 7, new Rook(Color.WHITE, new Position(7, 7)));
    }

    public void setPiece(int row, int col, Piece piece) {
        tiles[row][col].setPiece(piece);
    }

    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }
}
