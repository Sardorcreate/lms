package com.company.controller;

import com.company.exception.AllException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(AllException.class)
    public ResponseEntity<String> allException(AllException allException) {

        return ResponseEntity.ok().body(allException.getMessage());
    }
}
