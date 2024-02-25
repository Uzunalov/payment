package com.azericard.commonlib.exception;

import java.io.Serial;

public class InsufficientFundsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2407467361463433264L;

    public InsufficientFundsException(String s) {
        super(s);
    }
}