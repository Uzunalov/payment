package com.azericard.commonlib.exception;

import java.io.Serial;

public class AuthException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -7006712515283838465L;

    public AuthException(String s) {
        super(s);
    }
}
