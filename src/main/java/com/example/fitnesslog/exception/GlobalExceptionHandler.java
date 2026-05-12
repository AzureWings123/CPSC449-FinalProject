package com.example.fitnesslog.exception;

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

    /*
    Handles:
        - null body
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e) {
        Map<String, Object> constraintViolation = new HashMap<String, Object>();
        constraintViolation.put("status", 400);
        constraintViolation.put("message", "Field cannot be null.");
        constraintViolation.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(constraintViolation);
    }

    /*
    Handles:
        - Incorrect password
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleWrongInfo(AccessDeniedException e) {
        Map<String, Object> accessDenied = new HashMap<String, Object>();
        accessDenied.put("status", 401);
        accessDenied.put("message", e.getMessage());
        accessDenied.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(accessDenied);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> handleWrongUser(ForbiddenException e) {
        Map<String, Object> accessBlocked = new HashMap<String, Object>();
        accessBlocked.put("status", 403);
        accessBlocked.put("message", e.getMessage());
        accessBlocked.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(accessBlocked);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleWrongUser(DataIntegrityViolationException e) {
        Map<String, Object> dataIntegrityViolation = new HashMap<String, Object>();
        dataIntegrityViolation.put("status", 409);
        dataIntegrityViolation.put("message", e.getMessage());
        dataIntegrityViolation.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(dataIntegrityViolation);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNoInfo(ResourceNotFoundException e) {
        Map<String, Object> resourceNotFound = new HashMap<String, Object>();
        resourceNotFound.put("status", 404);
        resourceNotFound.put("message", e.getMessage());
        resourceNotFound.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(resourceNotFound);
    }
}
