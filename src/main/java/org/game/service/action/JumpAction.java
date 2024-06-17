package org.game.service.action;

import org.game.model.Cell;
import org.game.model.Player;
import org.game.model.jump.Jump;
import org.game.service.Board;

public class JumpAction implements PlayerAction {
    private final Board board;
    private final Player player;
    private final int startPosition;
    private final int endPosition;
    private final StringBuilder message;

    public JumpAction(Board board, Player player, int startPosition, int endPosition) {
        this.board = board;
        this.player = player;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.message = new StringBuilder(player.getName());
    }

    @Override
    public void execute() {
        Cell startCell = board.getCell(startPosition);
        Jump jump = startCell.getJump();

        if (jump.isIncremental()) {
            message.append(" rolled a ")
                    .append(startPosition - player.getCurrentPosition())
                    .append(" and climbed the ")
                    .append(jump.getType())
                    .append(" at ")
                    .append(startPosition)
                    .append(" and moved from ")
                    .append(startPosition)
                    .append(" to ")
                    .append(endPosition);
        } else {
            message.append(" rolled a ")
                    .append(startPosition - player.getCurrentPosition())
                    .append(" and bitten by ")
                    .append(jump.getType())
                    .append(" at ")
                    .append(startPosition)
                    .append(" and moved from ")
                    .append(startPosition)
                    .append(" to ")
                    .append(endPosition);
        }

        Cell previousCell = board.getCell(player.getCurrentPosition());
        previousCell.setPlayer(null);

        if (endPosition < (board.getSize() * board.getSize())) {
            Cell newCell = board.getCell(endPosition);
            newCell.setPlayer(player);
        }
        player.setCurrentPosition(endPosition);
    }

    @Override
    public boolean isWinningAction() {
        return endPosition == (board.getSize() * board.getSize());
    }

    @Override
    public String getMessage() {
        return message.toString();
    }
}
