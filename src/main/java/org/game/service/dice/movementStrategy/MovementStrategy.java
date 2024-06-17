package org.game.service.dice.movementStrategy;

import java.util.List;

public interface MovementStrategy {
    int getNextPosition(List<Integer> diceNumbers);
}
