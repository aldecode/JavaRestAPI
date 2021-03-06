package com.project.restapi.api.advices;

import com.project.restapi.api.models.ErrorResponseModel;
import com.project.restapi.businesslogic.exceptions.BadRequestException;
import com.project.restapi.businesslogic.exceptions.ResourceNotFoundException;
import com.project.restapi.businesslogic.exceptions.UnauthorizedException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Clock;
import java.time.Instant;

@ControllerAdvice
public class ExceptionHandlingAdvice {
    private final Clock clock = Clock.systemDefaultZone();

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponseModel> handleException(ExpiredJwtException ex) {
        return handleException(ex, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<ErrorResponseModel> handleException(UnsupportedJwtException ex) {
        return handleException(ex, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ErrorResponseModel> handleException(MalformedJwtException ex) {
        return handleException(ex, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseModel> handleException(ResourceNotFoundException ex) {
        return handleException(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponseModel> handleException(UnauthorizedException ex) {
        return handleException(ex, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseModel> handleException(BadRequestException ex) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseModel> handleException(Exception ex) {
        return handleException(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponseModel> handleException(Exception ex, HttpStatus httpStatus) {
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(Instant.now(clock).toString(), httpStatus.value(), httpStatus.name(), ex.getMessage());
        return new ResponseEntity<>(errorResponseModel, httpStatus);
    }
}