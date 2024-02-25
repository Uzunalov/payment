package com.azericard.commonlib.exception;

import java.io.Serial;

public class ResourceAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 9062775620001817882L;

    public ResourceAlreadyExistsException(String s) {
        super(s);
    }
}
