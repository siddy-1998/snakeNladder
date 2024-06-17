package org.game.service.action;

import org.game.model.Cell;
import org.game.model.Player;
import org.game.model.stay.Stay;
import org.game.service.Board;

public class StayAction implements PlayerAction {
    private final Board board;
    private final Player player;
    private final int newPosition;
    private final StringBuilder message;

    public StayAction(Board board, Player player, int position) {
        this.board = board;
        this.player = player;
        this.newPosition = position;
        this.message = new StringBuilder(player.getName());
    }

    @Override
    public void execute() {
        Cell cell = board.getCell(newPosition);
        Stay stay = cell.getStay();
        message.append(" rolled a ")
                .append(newPosition - player.getCurrentPosition())
                .append(" is stuck in a ")
                .append(stay.getType())
                .append(" for ")
                .append(stay.getStayTimeInTurns())
                .append(" turns");

        player.setNumOfWaitingTurn(stay.getStayTimeInTurns());
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
