package org.game.service;

import org.game.model.Player;
import org.game.service.action.PlayerAction;

public class MessagePrinter {
    public static void printTurnMessage(Player player) {
        System.out.println("\nPlayer turn :-> " + player.getName() + ", current position is: " + player.getCurrentPosition());
    }

    public static void printActionMessage(PlayerAction action) {
        System.out.println(action.getMessage());
    }

    public static void printWinnerMessage(Player winner) {
        System.out.println("\n<<< WINNER >>> is " + winner.getName());
    }

    public static void printSkippedTurnMessage(Player player) {
        System.out.println();
        System.out.println(player.getName() + " turn got skipped because of Mine, waiting turns left now = " + player.getNumOfWaitingTurn());
    }
}
