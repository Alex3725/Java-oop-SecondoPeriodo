package com.scacchi.bootstrap;

import com.scacchi.controller.GameController;
import com.scacchi.domain.game.Game;
import com.scacchi.logic.move.MoveValidator;
import com.scacchi.logic.rules.PromotionHandler;
import com.scacchi.ui.terminal.BoardRenderer;
import com.scacchi.ui.terminal.InputHandler;
import com.scacchi.ui.terminal.TerminalUI;
import com.scacchi.util.Constants;

public class Main {
    public static void main(String[] args) {
        System.out.println(Constants.APP_NAME + " - MVP console");

        Game game = Game.standardGame();
        TerminalUI terminalUI = new TerminalUI(new BoardRenderer(), new InputHandler());

        GameController controller = new GameController(
                game,
                terminalUI,
                new MoveValidator(),
                new PromotionHandler()
        );

        controller.start();
    }
}

