package org.game.model;

public class Player {
    private final String name;
    private int currentPosition;
    private int numOfWaitingTurn;


    public Player(String name) {
        this.name = name;
    }

    public Player(String name, int currentPosition) {
        this.name = name;
        this.currentPosition = currentPosition;
    }

    public String getName() {
        return name;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getNumOfWaitingTurn() {
        return numOfWaitingTurn;
    }

    public void setNumOfWaitingTurn(int numOfWaitingTurn) {
        this.numOfWaitingTurn = numOfWaitingTurn;
    }
}
