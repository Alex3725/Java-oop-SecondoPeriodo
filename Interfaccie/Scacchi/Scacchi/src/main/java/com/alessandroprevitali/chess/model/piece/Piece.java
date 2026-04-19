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

    // Restituisce le pseudo-mosse: rispettano il movimento del pezzo, ma non filtrano ancora lo scacco al proprio re.
    // Il filtro finale viene applicato a livello di servizio/regole.

    public abstract List<Move> getPseudoLegalMoves(Board board);

    public abstract PieceType getType();

    public Color getColor() { return color; }

    public Position getPosition() { return position; }

    public void setPosition(Position position) { this.position = position; }

    public boolean hasMoved() { return hasMoved; }

    public void setHasMoved(boolean v) { this.hasMoved = v; }

    public String getSymbol() {
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
