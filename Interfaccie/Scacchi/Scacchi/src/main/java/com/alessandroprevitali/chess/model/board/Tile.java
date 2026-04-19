package main.java.com.alessandroprevitali.chess.model.board;

public class Tile {

    private Position position;
    private Object piece; // lo sostituirai tu

    public Tile(Position position) {
        this.position = position;
    }

    public Object getPiece() { return piece; }
    public void setPiece(Object piece) { this.piece = piece; }

    public Position getPosition() { return position; }
}