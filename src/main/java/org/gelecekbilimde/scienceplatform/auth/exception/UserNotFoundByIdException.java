package org.gelecekbilimde.scienceplatform.auth.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractNotFoundException;

import java.io.Serial;

public final class UserNotFoundByIdException extends AbstractNotFoundException {

    @Serial
    private static final long serialVersionUID = -4173490660693121294L;

    public UserNotFoundByIdException(String id) {
        super("user does not found! id: " + id);
    }

}
