package com.example.fitnesslog.exception;

import jakarta.validation.constraints.Null;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.ConstraintViolationException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e) {
        Map<String, Object> constraintViolation = new HashMap<String, Object>();
        constraintViolation.put("status", HttpStatus.BAD_REQUEST);
        constraintViolation.put("message","Field can not be null.");
        constraintViolation.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(constraintViolation);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleWrongUser(AccessDeniedException e) {
        Map<String, Object> accessDenied = new HashMap<String, Object>();
        accessDenied.put("status", HttpStatus.FORBIDDEN);
        accessDenied.put("message",e.getMessage());
        accessDenied.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(accessDenied);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleWrongUser(DataIntegrityViolationException e) {
        Map<String, Object> dataIntegrityViolation = new HashMap<String, Object>();
        dataIntegrityViolation.put("status", HttpStatus.CONFLICT);
        dataIntegrityViolation.put("message",e.getMessage());
        dataIntegrityViolation.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(dataIntegrityViolation);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(ResourceNotFoundException e) {
        Map<String, Object> resourceNotFound = new HashMap<String, Object>();
        resourceNotFound.put("status", HttpStatus.NOT_FOUND);
        resourceNotFound.put("message",e.getMessage());
        resourceNotFound.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(resourceNotFound);
    }
}
