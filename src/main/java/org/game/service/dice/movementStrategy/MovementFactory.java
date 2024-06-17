package org.game.service.dice.movementStrategy;

public class MovementFactory {
    public MovementStrategy getMovementStrategy(String val) {
        return switch (val) {
            case "MAX" -> new MaxMovementStrategy();
            case "MIN" -> new MinMovementStrategy();
            default -> new SumMovementStrategy();
        };

    }
}
