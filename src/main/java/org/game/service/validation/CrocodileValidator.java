package org.game.service.validation;

import org.game.constants.JumpConstants;
import org.game.model.jump.Jump;

public class CrocodileValidator implements Validator {
    @Override
    public void validate(Jump jump, ValidationHelperService validationHelperService) throws Exception {
        int boardSize = validationHelperService.getBoardSize();
        int end = jump.getEnd();
        int start = jump.getStart();

        if (end < 0) {
            throw new RuntimeException("[ERROR] Please place crocodile in valid cell, i.e cell number greater than equal " +
                    "to " + JumpConstants.CROCODILE_STEP_BACK);
        }

        if (start > (boardSize * boardSize)) {
            throw new RuntimeException("[ERROR] Please place crocodile with start point " + start + " in valid " +
                    "cell b/w " + JumpConstants.CROCODILE_STEP_BACK
                    + " to " + ((boardSize * boardSize) - 1));
        }

        if(validationHelperService.getOccupiedPosition().contains(start) || validationHelperService.getOccupiedPosition().contains(end)) {
            throw new RuntimeException("Please Enter Valid Crocodile position, this position " + start + " and Step back position " + end + " is already occupied by other entity");
        }

        validationHelperService.getOccupiedPosition().add(end);
        validationHelperService.getOccupiedPosition().add(start);
    }
}
