package com.azericard.apigateway.exception;

import static com.azericard.commonlib.util.ResponseBuilder.buildErrorResponse;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.azericard.commonlib.exception.AuthException;
import com.azericard.commonlib.exception.CommonException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import reactor.core.publisher.Mono;

@RestControllerAdvice
@ConditionalOnWebApplication(type = Type.REACTIVE)
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public Mono<ResponseEntity<CommonException>> handleAuthException(
            AuthException exception) {
        return Mono.just(buildErrorResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED));
    }

    @ExceptionHandler(SignatureVerificationException.class)
    public Mono<ResponseEntity<CommonException>> handleSignatureVerificationException(
            SignatureVerificationException exception) {
        return Mono.just(buildErrorResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED));
    }

    @ExceptionHandler(TokenExpiredException.class)
    public Mono<ResponseEntity<CommonException>> handleTokenExpiredException(
            TokenExpiredException exception) {
        return Mono.just(buildErrorResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED));
    }
}
