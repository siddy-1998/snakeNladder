package org.game.model.jump;

public abstract class Jump {
    private int start;
    private int end;

    protected Jump(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public abstract String getType();

    public boolean isIncremental() {
        return false;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
