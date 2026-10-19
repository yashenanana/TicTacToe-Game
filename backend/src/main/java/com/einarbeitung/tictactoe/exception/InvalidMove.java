package com.einarbeitung.tictactoe.exception;

/**
 * Exception to be thrown when a requested move failed validation
 */
public class InvalidMove extends Throwable {
    private final String message;
    private final String debugMessage;

    public InvalidMove(String debugMessage){
        this.message = "The move is invalid. ";
        this.debugMessage = debugMessage;
    }

    @Override
    public String getMessage() {
        return message + debugMessage;
    }
}
