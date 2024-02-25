package com.azericard.commonlib.exception;

import java.io.Serial;

public class CommonClientException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -9048925458115754306L;

    public CommonClientException(String s) {
        super(s);
    }
}
