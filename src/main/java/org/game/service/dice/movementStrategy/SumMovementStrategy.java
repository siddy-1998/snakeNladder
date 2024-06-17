package org.game.service.dice.movementStrategy;

import java.util.List;

public class SumMovementStrategy implements MovementStrategy {
    @Override
    public int getNextPosition(List<Integer> diceNumbers) {
        return diceNumbers.stream().mapToInt(Integer::intValue).sum();
    }
}