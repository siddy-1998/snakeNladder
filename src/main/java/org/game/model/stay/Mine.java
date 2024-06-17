package org.game.model.stay;

public class Mine extends Stay {
    public Mine(int stayTimeInTurns, int position) {
        super(stayTimeInTurns, position);
    }

    @Override
    public String getType() {
        return "mine";
    }
}
