package org.game.service.dice;

import org.game.model.Dice;
import org.game.model.Player;
import org.game.service.dice.movementStrategy.MovementStrategy;

import java.util.List;

public abstract class DiceService {
    private final Dice dice;
    private final MovementStrategy diceMovementStrategy;

    public DiceService(Dice dice, MovementStrategy diceMovementStrategy) {
        this.dice = dice;
        this.diceMovementStrategy = diceMovementStrategy;
    }

    public abstract List<Integer> rollDice(Player p);

    public int getDiceNumber(List<Integer> diceValues) {
        return diceMovementStrategy.getNextPosition(diceValues);
    }

    public Dice getDice() {
        return dice;
    }

}
