package org.game.service.dice;

import org.game.model.Dice;
import org.game.model.Player;
import org.game.service.dice.movementStrategy.MovementStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SimulatedDiceRollingService extends DiceService {

    public SimulatedDiceRollingService(Dice dice, MovementStrategy diceMovementStrategy) {
        super(dice, diceMovementStrategy);
    }

    @Override
    public List<Integer> rollDice(Player p) {
        Dice dice = getDice();
        int diceCount = dice.getCount();
        int diceUsed = 0;
        List<Integer> diceValues = new ArrayList<>();

        while (diceUsed < diceCount) {
            int value = ThreadLocalRandom.current().nextInt(Dice.minNum, Dice.maxNum + 1);
            diceValues.add(value);
            diceUsed++;
        }

        return diceValues;
    }
}
