package com.scacchi.ui.terminal;

import com.scacchi.domain.game.Move;

public class InputHandler {
    public boolean isQuitCommand(String input) {
        return input != null && (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit"));
    }

    public Move parseMove(String input) {
        return Move.fromText(input);
    }
}

