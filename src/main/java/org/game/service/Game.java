package org.game.service;

import org.game.model.Dice;
import org.game.model.GameConfig;
import org.game.model.Player;
import org.game.service.action.PlayerAction;
import org.game.service.action.PlayerActionFactory;
import org.game.service.dice.DiceService;
import org.game.service.dice.ManualDiceRollingService;
import org.game.service.dice.SimulatedDiceRollingService;
import org.game.service.dice.movementStrategy.MovementFactory;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private Board board;
    private Dice dice;
    private Deque<Player> playersTurn = new LinkedList<>();
    private Player winner;
    private DiceService diceService;

    public Game(GameConfig config) {
        initializeGame(config);
    }

    private void initializeGame(GameConfig config) {
        board = new Board(config);
        dice = new Dice(config.getDiceCount());
        initializeDiceService(config);
        winner = null;
        addPlayers(config);
    }

    private void addPlayers(GameConfig config) {
        List<Player> players = config.getPlayers();
        playersTurn.addAll(players);
    }

    private void initializeDiceService(GameConfig config) {
        MovementFactory factory = new MovementFactory();
        String movementStrategy = config.getMovementStrategy();
        if (config.isManualOverride()) {
            diceService = new ManualDiceRollingService(dice, factory.getMovementStrategy(movementStrategy));
        } else {
            diceService = new SimulatedDiceRollingService(dice, factory.getMovementStrategy(movementStrategy));
        }
    }

    public void startGame() {
        while (!isGameCompleted()) {
            Player playerTurn = findPlayerTurn();
            MessagePrinter.printTurnMessage(playerTurn);

            List<Integer> diceNumbers = diceService.rollDice(playerTurn);
            int diceNumber = diceService.getDiceNumber(diceNumbers);

            int playerNewPosition = playerTurn.getCurrentPosition() + diceNumber;
            PlayerAction action = PlayerActionFactory.getAction(board, playerTurn, playerNewPosition);

            action.execute();
            MessagePrinter.printActionMessage(action);

            if (action.isWinningAction()) {
                winner = playerTurn;
            }
        }
        MessagePrinter.printWinnerMessage(winner);
    }

    private boolean isGameCompleted() {
        return winner != null;
    }

    private Player findPlayerTurn() {
        while (true) {
            Player player = playersTurn.pollFirst();
            int numWaitingTurn = player != null ? player.getNumOfWaitingTurn() : 0;
            if (numWaitingTurn > 0) {
                player.setNumOfWaitingTurn(numWaitingTurn - 1);
                playersTurn.offerLast(player);
                MessagePrinter.printSkippedTurnMessage(player);
            } else {
                playersTurn.offerLast(player);
                return player;
            }
        }
    }

}
