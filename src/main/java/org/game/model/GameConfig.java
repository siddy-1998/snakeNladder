package org.game.model;

import org.game.model.jump.Jump;
import org.game.model.stay.Stay;

import java.util.List;

public class GameConfig {
    private int boardSize;
    private List<Jump> snakes;
    private List<Jump> ladders;
    private List<Jump> crocodiles;
    private List<Stay> mines;
    private List<Player> players;
    private int diceCount;
    private String movementStrategy;
    private boolean manualOverride;

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public List<Jump> getSnakes() {
        return snakes;
    }

    public void setSnakes(List<Jump> snakes) {
        this.snakes = snakes;
    }

    public List<Jump> getLadders() {
        return ladders;
    }

    public void setLadders(List<Jump> ladders) {
        this.ladders = ladders;
    }

    public List<Jump> getCrocodiles() {
        return crocodiles;
    }

    public void setCrocodiles(List<Jump> crocodiles) {
        this.crocodiles = crocodiles;
    }

    public List<Stay> getMines() {
        return mines;
    }

    public void setMines(List<Stay> mines) {
        this.mines = mines;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getDiceCount() {
        return diceCount;
    }

    public void setDiceCount(int diceCount) {
        this.diceCount = diceCount;
    }

    public String getMovementStrategy() {
        return movementStrategy;
    }

    public void setMovementStrategy(String movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public boolean isManualOverride() {
        return manualOverride;
    }

    public void setManualOverride(boolean manualOverride) {
        this.manualOverride = manualOverride;
    }

}
