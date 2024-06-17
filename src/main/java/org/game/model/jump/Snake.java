package org.game.model.jump;

public class Snake extends Jump {
    public Snake(int start, int end) {
        super(start, end);
    }

    @Override
    public String getType() {
        return "snake";
    }
}
