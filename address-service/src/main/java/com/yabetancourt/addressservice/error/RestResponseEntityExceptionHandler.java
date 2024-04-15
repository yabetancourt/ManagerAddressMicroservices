package com.yabetancourt.addressservice.error;

import org.hibernate.StaleObjectStateException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { AddressNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { StaleObjectStateException.class })
    protected ResponseEntity<Object> handleOptimisticLocking(RuntimeException ex, WebRequest request) {
        String errorMessage = "Error: Another user has modified this address since you last loaded it. Please reload the data and try again.";
        return ResponseEntity.internalServerError().body(errorMessage);
    }

    @ExceptionHandler(value = { DataIntegrityViolationException.class })
    protected ResponseEntity<Object> handleDatabaseExceptions(RuntimeException ex, WebRequest request) {
        String errorMessage = "Data Integrity error: " + ex.getLocalizedMessage();
        return ResponseEntity.internalServerError().body(errorMessage);
    }

}