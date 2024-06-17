package org.game.model;

import org.game.constants.DiceConstants;

public class Dice {
    public final static int minNum = DiceConstants.DICE_MIN;
    public final static int maxNum = DiceConstants.DICE_MAX;
    private int count;

    public Dice(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
