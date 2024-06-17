package org.game.service.validation;

import org.game.model.jump.Jump;

public class SnakeValidator implements Validator {
    @Override
    public void validate(Jump jump, ValidationHelperService validationHelperService) {

      int head = jump.getStart();
      int tail = jump.getEnd();

      validationHelperService.validatePositionThrowException(head);
      validationHelperService.validatePositionThrowException(tail);

      if(tail >= head) {
          throw new RuntimeException("Enter valid head and tail combo for snake with head= " + head +" tail= " + tail);
      }

      if(validationHelperService.getOccupiedPosition().contains(head) || validationHelperService.getOccupiedPosition().contains(tail)) {
          throw new RuntimeException("Please Enter Valid Snake position, this position head = " +head + " or tail= "+ tail+ " is already occupied by other entity");
      }

      validationHelperService.getOccupiedPosition().add(head);
      validationHelperService.getOccupiedPosition().add(tail);
    }
}
