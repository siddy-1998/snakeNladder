package org.game.model.jump;

public class Crocodile extends Jump {
    public Crocodile(int start, int end) {
        super(start, end);
    }

    @Override
    public String getType() {
        return "crocodile";
    }
}
