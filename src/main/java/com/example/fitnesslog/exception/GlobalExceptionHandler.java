package com.example.fitnesslog.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("{ \n\"status\": 400, \n\"message\": field can not be null \n}");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleWrongUser(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleWrongUser(NullPointerException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNoInfo(NullPointerException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
