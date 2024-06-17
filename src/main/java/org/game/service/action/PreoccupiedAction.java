package org.game.service.action;

import org.game.constants.BoardConstants;
import org.game.model.Cell;
import org.game.model.Player;
import org.game.service.Board;

public class PreoccupiedAction implements PlayerAction {
    private final Board board;
    private final Player player;
    private final Player existingPlayer;
    private final int newPosition;
    private final StringBuilder message;

    public PreoccupiedAction(Board board, Player player, Player existingPlayer, int newPosition) {
        this.board = board;
        this.player = player;
        this.existingPlayer = existingPlayer;
        this.newPosition = newPosition;
        this.message = new StringBuilder(player.getName());
    }

    @Override
    public void execute() {
        message.append(" rolled a ")
                .append(newPosition - player.getCurrentPosition())
                .append(" and landed on a cell preoccupied by ")
                .append(existingPlayer.getName())
                .append(". Hence ")
                .append(existingPlayer.getName())
                .append(" moved to starting position ")
                .append(BoardConstants.BOARD_START);

        Cell previousCell = board.getCell(player.getCurrentPosition());
        previousCell.setPlayer(null);

        Cell newCell = board.getCell(newPosition);
        newCell.setPlayer(player);
        player.setCurrentPosition(newPosition);

        existingPlayer.setCurrentPosition(BoardConstants.BOARD_START);
        existingPlayer.setNumOfWaitingTurn(0);
        board.getCell(BoardConstants.BOARD_START).setPlayer(existingPlayer);
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
