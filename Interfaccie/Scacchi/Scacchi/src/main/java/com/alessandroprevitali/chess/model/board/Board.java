package main.java.com.alessandroprevitali.chess.model.board;

public class Board {

    private Tile[][] tiles = new Tile[8][8];

    public Board() {
        init();
    }

    private void init() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                tiles[r][c] = new Tile(new Position(r, c));
            }
        }
    }

    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }
}