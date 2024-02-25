package com.azericard.commonlib.util;

import com.azericard.commonlib.exception.CommonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

    public static <T> ResponseEntity<T> buildResponse(T body, HttpStatus status) {
        return new ResponseEntity<>(body, status);
    }

    public static <T> ResponseEntity<T> buildResponse(T body) {
        return buildResponse(body, HttpStatus.OK);
    }

    public static ResponseEntity<HttpStatus> buildResponse() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public static ResponseEntity<HttpStatus> buildResponse(HttpStatus status) {
        return new ResponseEntity<>(status);
    }

    public static ResponseEntity<CommonException> buildErrorResponse(String message, HttpStatus status) {
        return new ResponseEntity<>(new CommonException(message, status), status);
    }
}
