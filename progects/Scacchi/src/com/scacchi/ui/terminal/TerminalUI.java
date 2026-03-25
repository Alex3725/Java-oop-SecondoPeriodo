package com.scacchi.ui.terminal;

import com.scacchi.domain.game.Game;

import java.util.Scanner;

public class TerminalUI {
    private final BoardRenderer boardRenderer;
    private final InputHandler inputHandler;
    private final Scanner scanner;

    public TerminalUI(BoardRenderer boardRenderer, InputHandler inputHandler) {
        this.boardRenderer = boardRenderer;
        this.inputHandler = inputHandler;
        this.scanner = new Scanner(System.in);
    }

    public void render(Game game) {
        System.out.println(boardRenderer.render(game.getBoard()));
        System.out.println("Turno: " + game.getCurrentPlayer());
        System.out.println("Inserisci mossa (es: e2 e4) o 'quit':");
    }

    public String readInput() {
        return scanner.nextLine().trim();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public InputHandler inputHandler() {
        return inputHandler;
    }
}

