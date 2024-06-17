package org.game.service.validation;

import org.game.constants.BoardConstants;
import org.game.constants.DiceConstants;

import java.util.HashSet;
import java.util.Set;

public class ValidationHelperService {
    private final Set<Integer> snakeHeads = new HashSet<>();
    private final Set<Integer> snakeTails = new HashSet<>();
    private final Set<Integer> ladderStarts = new HashSet<>();
    private final Set<Integer> ladderEnds = new HashSet<>();
    private final Set<Integer> crocodilePositions = new HashSet<>();
    private final Set<Integer> minePositions = new HashSet<>();
    private Set<Integer> occupiedPosition = new HashSet<>();
    private int boardSize;

    public Set<Integer> getOccupiedPosition() {
        return occupiedPosition;
    }

    public void setOccupiedPosition(Set<Integer> occupiedPosition) {
        this.occupiedPosition = occupiedPosition;
    }

    public void validateMovementStrategy(String movementStrategy) {
        if (!movementStrategy.equals("SUM") && !movementStrategy.equals("MAX") && !movementStrategy.equals("MIN")) {
            throw new RuntimeException("[ERROR] please enter correct movement strategy among [SUM, MAX, MIN]");
        }
    }

    public boolean validatePosition(int position) {
        return position >= BoardConstants.BOARD_START && position < (boardSize * boardSize);
    }

    public void validatePositionThrowException(int position) {
        if (!validatePosition(position)) {
            throw new RuntimeException("Enter valid position b/w " + BoardConstants.BOARD_START +
                    " to " + (boardSize * boardSize - 1));
        }
    }


    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        if (boardSize < BoardConstants.MIN_SIZE || boardSize > BoardConstants.MAX_SIZE) {
            throw new RuntimeException("Enter valid board size b/w " + BoardConstants.MIN_SIZE + " and  " + BoardConstants.MAX_SIZE);
        }
        this.boardSize = boardSize;
    }

    public void validateDiceCount(int diceCount) {
        if (diceCount <= 0 || diceCount > DiceConstants.MAX_DICE) {
            throw new RuntimeException("Enter Valid dice count b/w 1 and " + DiceConstants.MAX_DICE);
        }
    }

    public void validateMines(int position) {
        if (!validatePosition(position)) {
            throw new RuntimeException("Enter Valid Mile position b/w " + BoardConstants.BOARD_START +
                    " to " + (boardSize * boardSize - 1));
        }

        if (occupiedPosition.contains(position)) {
            throw new RuntimeException("Please Enter Valid Mile position, this position " + position + " is already occupied");
        }

        occupiedPosition.add(position);
    }


}
