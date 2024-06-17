package org.game.service.validation;

import org.game.model.jump.Jump;

public interface Validator {
    void validate(Jump jump, ValidationHelperService validationHelperService) throws Exception;
}
