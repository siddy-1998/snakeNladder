package org.game.service.dice;

import org.game.model.Dice;
import org.game.model.Player;
import org.game.service.dice.movementStrategy.MovementStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualDiceRollingService extends DiceService {

    public ManualDiceRollingService(Dice dice, MovementStrategy diceMovementStrategy) {
        super(dice, diceMovementStrategy);
    }

    @Override
    public List<Integer> rollDice(Player p) {
        Dice dice = getDice();
        int diceCount = dice.getCount();
        int diceUsed = 1;
        List<Integer> diceValues = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (diceUsed <= diceCount) {
            System.out.println(p.getName() + ", Please enter the dice value (b/w 1-6) for dice number " + diceUsed);
            int value = sc.nextInt();
            if (value < Dice.minNum || value > Dice.maxNum) {
                System.out.println("[ERROR] Please enter values b/w " + Dice.minNum + " to " + Dice.maxNum);
                continue;
            }
            diceValues.add(value);
            diceUsed++;
        }

        return diceValues;
    }
}
