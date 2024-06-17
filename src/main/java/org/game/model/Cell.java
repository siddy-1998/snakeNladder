package org.game.model;

import org.game.model.jump.Jump;
import org.game.model.stay.Stay;

public class Cell {
    private Jump jump;
    private Stay stay;
    private Player player;

    public Cell(Jump jump, Stay stay) {
        this.jump = jump;
        this.stay = stay;
    }

    public Cell() {
    }

    public Jump getJump() {
        return jump;
    }

    public void setJump(Jump jump) {
        this.jump = jump;
    }

    public Stay getStay() {
        return stay;
    }

    public void setStay(Stay stay) {
        this.stay = stay;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
