package org.game.service.action;

import org.game.model.Cell;
import org.game.model.Player;
import org.game.model.jump.Jump;
import org.game.service.Board;

public class PlayerActionFactory {

    public static PlayerAction getAction(Board board, Player player, int newPosition) {
        if (newPosition == (board.getSize() * board.getSize())) {
            return new WonAction(board, player, newPosition);
        }

        if (newPosition > board.getSize() * board.getSize()) {
            return new InvalidMoveAction(board, player, newPosition);
        }

        Cell newCell = board.getCell(newPosition);

        if (newCell.getJump() != null) {
            Jump jump = newCell.getJump();
            int endPosition = jump.getEnd();
            if (jump.getStart() == newPosition) {
                return new JumpAction(board, player, newPosition, endPosition);
            }
            return new MoveAction(board, player, newPosition);
        }

        if (newCell.getStay() != null) {
            return new StayAction(board, player, newPosition);
        }

        if (newCell.getPlayer() != null) {
            return new PreoccupiedAction(board, player, newCell.getPlayer(), newPosition);
        }

        return new MoveAction(board, player, newPosition);
    }
}
