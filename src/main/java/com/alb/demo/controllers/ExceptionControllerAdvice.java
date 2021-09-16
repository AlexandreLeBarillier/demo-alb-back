package com.alb.demo.controllers;

import com.alb.demo.exceptions.UnknownIdException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnknownIdException.class)
    public ResponseEntity handleUnknowId() {
        return ResponseEntity.notFound().build();
    }
}
