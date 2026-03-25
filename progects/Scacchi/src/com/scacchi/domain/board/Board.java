package com.scacchi.domain.board;

import com.scacchi.domain.pieces.Bishop;
import com.scacchi.domain.pieces.King;
import com.scacchi.domain.pieces.Knight;
import com.scacchi.domain.pieces.Pawn;
import com.scacchi.domain.pieces.Piece;
import com.scacchi.domain.pieces.PieceColor;
import com.scacchi.domain.pieces.Queen;
import com.scacchi.domain.pieces.Rook;
import com.scacchi.util.Constants;

public class Board {
    private final Piece[][] grid;

    public Board() {
        this.grid = new Piece[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
    }

    public Piece getPiece(Position position) {
        return grid[position.row()][position.col()];
    }

    public void setPiece(Position position, Piece piece) {
        grid[position.row()][position.col()] = piece;
    }

    public void movePiece(Position from, Position to) {
        Piece piece = getPiece(from);
        setPiece(to, piece);
        setPiece(from, null);
    }

    public static Board initialBoard() {
        Board board = new Board();

        for (int col = 0; col < Constants.BOARD_SIZE; col++) {
            board.grid[1][col] = new Pawn(PieceColor.BLACK);
            board.grid[6][col] = new Pawn(PieceColor.WHITE);
        }

        board.grid[0][0] = new Rook(PieceColor.BLACK);
        board.grid[0][1] = new Knight(PieceColor.BLACK);
        board.grid[0][2] = new Bishop(PieceColor.BLACK);
        board.grid[0][3] = new Queen(PieceColor.BLACK);
        board.grid[0][4] = new King(PieceColor.BLACK);
        board.grid[0][5] = new Bishop(PieceColor.BLACK);
        board.grid[0][6] = new Knight(PieceColor.BLACK);
        board.grid[0][7] = new Rook(PieceColor.BLACK);

        board.grid[7][0] = new Rook(PieceColor.WHITE);
        board.grid[7][1] = new Knight(PieceColor.WHITE);
        board.grid[7][2] = new Bishop(PieceColor.WHITE);
        board.grid[7][3] = new Queen(PieceColor.WHITE);
        board.grid[7][4] = new King(PieceColor.WHITE);
        board.grid[7][5] = new Bishop(PieceColor.WHITE);
        board.grid[7][6] = new Knight(PieceColor.WHITE);
        board.grid[7][7] = new Rook(PieceColor.WHITE);

        return board;
    }
}

