package com.einarbeitung.tictactoe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice //used to manage multiple exception handlers in one location
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler (InvalidMove.class)
    protected ResponseEntity<Object> handleInvalidMove(InvalidMove ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
