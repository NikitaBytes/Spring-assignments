package com.example.library.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.library.service.NotFoundException;

/**
 * Global exception handler for the application.
 * This class provides centralized exception handling across all controller classes.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles NotFoundException.
     *
     * @param ex The NotFoundException instance.
     * @return ResponseEntity containing an error message and HTTP status NOT_FOUND.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(NotFoundException ex) {
        log.info("Resource not found: {}", ex.getMessage());
        return new ResponseEntity<>(
            Collections.singletonMap("error", ex.getMessage()),
            HttpStatus.NOT_FOUND
        );
    }

    /**
     * Handles MethodArgumentNotValidException.
     *
     * @param ex The MethodArgumentNotValidException instance.
     * @return ResponseEntity containing a map of validation errors and HTTP status BAD_REQUEST.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.warn("Validation failed: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            errors.put(fe.getField(), fe.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles MethodArgumentTypeMismatchException.
     *
     * @param ex The MethodArgumentTypeMismatchException instance.
     * @return ResponseEntity containing an error message and HTTP status BAD_REQUEST.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String msg = String.format("Invalid value '%s' for parameter '%s'",
            ex.getValue(), ex.getName());
        log.warn("Type mismatch: {}", msg);
        return new ResponseEntity<>(
            Collections.singletonMap("error", msg),
            HttpStatus.BAD_REQUEST
        );
    }

    /**
     * Handles all other exceptions.
     *
     * @param ex The Exception instance.
     * @return ResponseEntity containing a generic error message and HTTP status INTERNAL_SERVER_ERROR.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAll(Exception ex) {
        log.error("Unhandled exception caught by GlobalExceptionHandler", ex);
        return new ResponseEntity<>(
            Collections.singletonMap("error", "Internal server error"),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}