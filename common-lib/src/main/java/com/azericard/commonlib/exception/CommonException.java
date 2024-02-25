package com.azericard.commonlib.exception;

import java.time.Instant;
import java.util.Date;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommonException {

    private final HttpStatus code;
    private final String message;
    private final int status;
    private final Date timestamp;

    public CommonException(String message, HttpStatus code) {
        this.message = message;
        this.code = code;
        timestamp = Date.from(Instant.now());
        status = code.value();
    }
}
