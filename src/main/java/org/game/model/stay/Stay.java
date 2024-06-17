package org.game.model.stay;

public abstract class Stay {
    private int stayTimeInTurns;
    private int position;

    protected Stay(int stayTimeInTurns, int position) {
        this.stayTimeInTurns = stayTimeInTurns;
        this.position = position;
    }

    public abstract String getType();

    public int getStayTimeInTurns() {
        return stayTimeInTurns;
    }

    public void setStayTimeInTurns(int stayTimeInTurns) {
        this.stayTimeInTurns = stayTimeInTurns;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
