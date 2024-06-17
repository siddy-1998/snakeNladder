package org.game.service.action;

import org.game.model.Player;
import org.game.service.Board;

public class InvalidMoveAction implements PlayerAction {
    private final Board board;
    private final Player player;
    private final int invalidPosition;
    private final StringBuilder message;

    public InvalidMoveAction(Board board, Player player, int invalidPosition) {
        this.board = board;
        this.player = player;
        this.invalidPosition = invalidPosition;
        this.message = new StringBuilder(player.getName());
    }

    @Override
    public void execute() {
        int requiredNumber = (board.getSize() * board.getSize()) - player.getCurrentPosition();
        message.append(" rolled a ")
                .append(invalidPosition - player.getCurrentPosition())
                .append(" but can't move. To move it requires dice number <= ")
                .append(requiredNumber)
                .append(". Try in next turn.");
    }

    @Override
    public boolean isWinningAction() {
        return false;
    }

    @Override
    public String getMessage() {
        return message.toString();
    }
}
