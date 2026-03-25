package com.scacchi.domain.game;

import com.scacchi.domain.board.Position;

public class Move {
    private final Position from;
    private final Position to;

    public Move(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    public static Move fromText(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Move text is null");
        }

        String normalized = text.trim().toLowerCase();
        if (normalized.contains(" ")) {
            String[] parts = normalized.split("\\s+");
            if (parts.length == 2) {
                return new Move(Position.fromAlgebraic(parts[0]), Position.fromAlgebraic(parts[1]));
            }
        }

        if (normalized.length() == 4) {
            Position from = Position.fromAlgebraic(normalized.substring(0, 2));
            Position to = Position.fromAlgebraic(normalized.substring(2, 4));
            return new Move(from, to);
        }

        throw new IllegalArgumentException("Invalid move format: " + text);
    }

    @Override
    public String toString() {
        return from + " -> " + to;
    }
}

