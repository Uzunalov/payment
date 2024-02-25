package com.azericard.card.exception;

import java.io.Serial;

public class ExpiredCardException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2449600800787938026L;

    public ExpiredCardException(String s) {
        super(s);
    }
}
