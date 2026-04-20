package main.java.com.alessandroprevitali.chess.model.piece;

import main.java.com.alessandroprevitali.chess.model.board.Board;
import main.java.com.alessandroprevitali.chess.model.board.Position;
import main.java.com.alessandroprevitali.chess.model.move.Move;
import java.util.List;

public abstract class Piece {
    // Colore del pezzo (WHITE o BLACK): determina direzione e catture valide.
    protected Color color;
    // Posizione corrente del pezzo sulla scacchiera.
    protected Position position;
    // Indica se il pezzo si e' gia mosso almeno una volta (utile per arrocco e doppio passo del pedone).
    protected boolean hasMoved = false;

    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    /**
     * Restituisce le pseudo-mosse del pezzo: rispettano la geometria del movimento,
     * ma non filtrano ancora le mosse che lasciano il proprio re in scacco.
     *
     * @param board board corrente
     * @return lista di pseudo-mosse geometricamente valide
     */
    public abstract List<Move> getPseudoLegalMoves(Board board);

    /**
     * Restituisce il tipo concreto del pezzo.
     *
     * @return tipo enum del pezzo
     */
    public abstract PieceType getType();

    /**
     * Restituisce il colore del pezzo.
     *
     * @return colore del pezzo
     */
    public Color getColor() { return color; }

    /**
     * Restituisce la posizione corrente del pezzo.
     *
     * @return posizione attuale
     */
    public Position getPosition() { return position; }

    /**
     * Aggiorna la posizione logica corrente del pezzo.
     *
     * @param position nuova posizione del pezzo
     */
    public void setPosition(Position position) { this.position = position; }

    /**
     * Indica se il pezzo è già stato mosso almeno una volta.
     *
     * @return true se il pezzo è già stato mosso, false altrimenti
     */
    public boolean hasMoved() { return hasMoved; }

    /**
     * Aggiorna il flag che indica se il pezzo si è già mosso almeno una volta.
     *
     * @param v nuovo valore del flag hasMoved
     */
    public void setHasMoved(boolean v) { this.hasMoved = v; }

    /**
     * Restituisce il simbolo Unicode usato per rappresentare il pezzo nella UI.
     *
     * @return simbolo Unicode del pezzo
     */
    public String getSymbol() {
        // Simboli Unicode usati nella UI per disegnare i pezzi.
        if (color == Color.WHITE) {
            return switch (getType()) {
                case KING -> "♔"; case QUEEN -> "♕"; case ROOK -> "♖";
                case BISHOP -> "♗"; case KNIGHT -> "♘"; case PAWN -> "♙";
            };
        } else {

            return switch (getType()) {
                case KING -> "♚"; case QUEEN -> "♛"; case ROOK -> "♜";
                case BISHOP -> "♝"; case KNIGHT -> "♞"; case PAWN -> "♟";
            };
        }
    }
}
