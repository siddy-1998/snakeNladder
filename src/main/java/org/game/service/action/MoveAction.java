package org.game.service.action;


import org.game.model.Cell;
import org.game.model.Player;
import org.game.service.Board;

public class MoveAction implements PlayerAction {
    private final Board board;
    private final Player player;
    private final int newPosition;
    private final StringBuilder message;

    public MoveAction(Board board, Player player, int newPosition) {
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

        Cell previousCell = board.getCell(player.getCurrentPosition());
        previousCell.setPlayer(null);

        Cell newCell = board.getCell(newPosition);
        newCell.setPlayer(player);
        player.setCurrentPosition(newPosition);
    }

    @Override
    public boolean isWinningAction() {
        return newPosition == (board.getSize() * board.getSize());
    }

    @Override
    public String getMessage() {
        return message.toString();
    }
}
