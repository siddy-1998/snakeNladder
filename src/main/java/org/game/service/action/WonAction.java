package org.game.service.action;

import org.game.model.Player;
import org.game.service.Board;

public class WonAction implements PlayerAction {
    private final Board board;
    private final Player player;
    private final int newPosition;
    private final StringBuilder message;

    public WonAction(Board board, Player player, int newPosition) {
        this.board = board;
        this.player = player;
        this.newPosition = newPosition;
        this.message = new StringBuilder(player.getName());
    }

    @Override
    public void execute() {
        message.append(" rolled a ")
                .append(newPosition - player.getCurrentPosition())
                .append(" and moved from ")
                .append(player.getCurrentPosition())
                .append(" to ")
                .append(newPosition);
    }

    @Override
    public boolean isWinningAction() {
        return true;
    }

    @Override
    public String getMessage() {
        return message.toString();
    }
}
