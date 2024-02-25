package com.azericard.commonlib.exception;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3091893871264280427L;

    public ResourceNotFoundException(String s) {
        super(s);
    }
}
