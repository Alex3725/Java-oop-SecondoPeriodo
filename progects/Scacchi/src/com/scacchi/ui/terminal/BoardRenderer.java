package com.scacchi.ui.terminal;

import com.scacchi.domain.board.Board;
import com.scacchi.domain.board.Position;
import com.scacchi.domain.pieces.Piece;
import com.scacchi.util.Constants;

public class BoardRenderer {
    public String render(Board board) {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < Constants.BOARD_SIZE; row++) {
            int rank = Constants.BOARD_SIZE - row;
            sb.append(rank).append(" ");

            for (int col = 0; col < Constants.BOARD_SIZE; col++) {
                Piece piece = board.getPiece(new Position(row, col));
                sb.append(piece == null ? "." : piece.getSymbol()).append(" ");
            }
            sb.append('\n');
        }

        sb.append("  a b c d e f g h\n");
        return sb.toString();
    }
}

