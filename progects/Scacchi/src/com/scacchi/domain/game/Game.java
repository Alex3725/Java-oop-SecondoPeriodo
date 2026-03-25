package com.scacchi.domain.game;

import com.scacchi.domain.board.Board;
import com.scacchi.domain.pieces.PieceColor;

public class Game {
    private final Board board;
    private PieceColor currentPlayer;
    private GameState gameState;

    public Game(Board board, PieceColor currentPlayer, GameState gameState) {
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.gameState = gameState;
    }

    public static Game standardGame() {
        return new Game(Board.initialBoard(), PieceColor.WHITE, GameState.ONGOING);
    }

    public Board getBoard() {
        return board;
    }

    public PieceColor getCurrentPlayer() {
        return currentPlayer;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void applyMove(Move move) {
        board.movePiece(move.getFrom(), move.getTo());
    }

    public void nextTurn() {
        currentPlayer = currentPlayer.opposite();
    }

    public void resign() {
        gameState = GameState.RESIGNED;
    }
}

