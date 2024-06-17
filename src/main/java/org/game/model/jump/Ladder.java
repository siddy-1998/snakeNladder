package org.game.model.jump;

public class Ladder extends Jump {
    public Ladder(int start, int end) {
        super(start, end);
    }

    @Override
    public String getType() {
        return "ladder";
    }

    @Override
    public boolean isIncremental() {
        return true;
    }
}
