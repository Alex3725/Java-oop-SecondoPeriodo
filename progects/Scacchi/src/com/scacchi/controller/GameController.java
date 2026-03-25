package com.scacchi.controller;

import com.scacchi.domain.game.Game;
import com.scacchi.domain.game.GameState;
import com.scacchi.domain.game.Move;
import com.scacchi.logic.move.MoveValidator;
import com.scacchi.logic.move.ValidationResult;
import com.scacchi.logic.rules.PromotionHandler;
import com.scacchi.ui.terminal.TerminalUI;

public class GameController {
    private final Game game;
    private final TerminalUI terminalUI;
    private final MoveValidator moveValidator;
    private final PromotionHandler promotionHandler;

    public GameController(
            Game game,
            TerminalUI terminalUI,
            MoveValidator moveValidator,
            PromotionHandler promotionHandler
    ) {
        this.game = game;
        this.terminalUI = terminalUI;
        this.moveValidator = moveValidator;
        this.promotionHandler = promotionHandler;
    }

    public void start() {
        while (game.getGameState() == GameState.ONGOING) {
            terminalUI.render(game);
            String input = terminalUI.readInput();

            if (terminalUI.inputHandler().isQuitCommand(input)) {
                game.resign();
                terminalUI.showMessage("Partita terminata.");
                break;
            }

            try {
                Move move = terminalUI.inputHandler().parseMove(input);
                ValidationResult validation = moveValidator.validate(game, move);
                if (!validation.isValid()) {
                    terminalUI.showMessage("Mossa non valida: " + validation.message());
                    continue;
                }

                game.applyMove(move);
                promotionHandler.applyPromotionIfNeeded(game, move.getTo());
                game.nextTurn();
            } catch (IllegalArgumentException ex) {
                terminalUI.showMessage("Input non valido: " + ex.getMessage());
            }
        }
    }
}

