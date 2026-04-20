package main.java.com.alessandroprevitali.chess.model.board;

import main.java.com.alessandroprevitali.chess.model.piece.Color;
import main.java.com.alessandroprevitali.chess.model.piece.Piece;
import main.java.com.alessandroprevitali.chess.model.piece.impl.*;

public class Board {

    // Matrice 8x8 che rappresenta tutte le caselle della scacchiera.
    private Tile[][] tiles = new Tile[8][8];

    /**
     * Costruisce una nuova board con tutte le caselle e il setup iniziale dei pezzi.
     */
    public Board() {
        // Prima creo tutte le caselle vuote, poi posiziono i pezzi iniziali.
        init();
        initializePieces();
    }

    /**
     * Inizializza la matrice delle caselle creando una Tile per ogni coordinata.
     */
    private void init() {
        // Ogni Tile conosce la propria posizione interna.
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                tiles[r][c] = new Tile(new Position(r, c));
            }
        }
    }

    /**
     * Posiziona i pezzi nella configurazione iniziale standard.
     */
    private void initializePieces() {
        // Schieramento iniziale dei pezzi neri.

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

        // Schieramento iniziale dei pezzi bianchi.

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

    /**
     * Imposta il pezzo nella casella indicata.
     *
     * @param row riga della casella
     * @param col colonna della casella
     * @param piece pezzo da inserire, oppure null per svuotare la casella
     */
    public void setPiece(int row, int col, Piece piece) {
        // Aggiorna solo il contenuto della casella, senza cambiare la posizione logica.
        tiles[row][col].setPiece(piece);
    }

    /**
     * Restituisce la casella alla coordinata richiesta.
     *
     * @param row riga della casella
     * @param col colonna della casella
     * @return Tile presente alla coordinata indicata
     */
    public Tile getTile(int row, int col) {
        // Accesso diretto alla casella richiesta.
        return tiles[row][col];
    }
}
