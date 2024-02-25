package com.azericard.card.exception;

import com.azericard.commonlib.exception.CommonErrorHandler;
import com.azericard.commonlib.exception.CommonException;
import com.azericard.commonlib.util.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler extends CommonErrorHandler {

    @ExceptionHandler(ExpiredCardException.class)
    public ResponseEntity<CommonException> handleExpiredCardException(ExpiredCardException exception) {
        return ResponseBuilder.buildErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
