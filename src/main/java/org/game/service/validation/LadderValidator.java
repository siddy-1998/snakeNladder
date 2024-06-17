package org.game.service.validation;

import org.game.constants.BoardConstants;
import org.game.model.jump.Jump;

public class LadderValidator implements Validator {
    @Override
    public void validate(Jump jump, ValidationHelperService validationHelperService) {
        int boardSize = validationHelperService.getBoardSize();
        int bottom = jump.getStart();
        int top = jump.getEnd();

        validationHelperService.validatePositionThrowException(bottom);
        if (top < BoardConstants.BOARD_START || top > (boardSize * boardSize)) {
            throw new RuntimeException("Enter valid position b/w " + BoardConstants.BOARD_START +
                    " to " + (boardSize * boardSize - 1));
        }

        if(top <= bottom) {
            throw new RuntimeException("Enter valid top and bottom combo for ladder with bottom= " + bottom +" top= " + top);
        }

        if(validationHelperService.getOccupiedPosition().contains(bottom) || validationHelperService.getOccupiedPosition().contains(top)) {
            throw new RuntimeException("Please Enter Valid Ladder position, this position bottom = " +bottom + " or top= "+ top+ " is already occupied by other entity");
        }

        validationHelperService.getOccupiedPosition().add(bottom);
        validationHelperService.getOccupiedPosition().add(top);

    }
}
