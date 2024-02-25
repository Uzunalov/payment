package com.azericard.commonlib.exception;

import static com.azericard.commonlib.util.ResponseBuilder.buildErrorResponse;
import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Component
@RestControllerAdvice
public class CommonErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CommonException> handleResourceNotFoundException(
            ResourceNotFoundException exception) {
        return buildErrorResponse(exception.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<CommonException> handleResourceAlreadyExistsException(
            ResourceAlreadyExistsException exception) {
        return buildErrorResponse(exception.getMessage(), CONFLICT);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<CommonException> handleAuthException(AuthException exception) {
        return buildErrorResponse(exception.getMessage(), UNAUTHORIZED);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<CommonException> handleInsufficientFundsException(
            InsufficientFundsException exception) {
        return buildErrorResponse(exception.getMessage(), PAYMENT_REQUIRED);
    }

    @ExceptionHandler(CommonClientException.class)
    public ResponseEntity<CommonException> handleCommonClientException(CommonClientException exception) {
        return buildErrorResponse(exception.getMessage(), BAD_REQUEST);
    }
}
