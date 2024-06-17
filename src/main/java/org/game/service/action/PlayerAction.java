package org.game.service.action;

public interface PlayerAction {
    void execute();

    boolean isWinningAction();

    String getMessage();
}