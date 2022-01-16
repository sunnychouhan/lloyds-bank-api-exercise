package com.lloydsbank.api.exception;

public class ApiAccessException extends RuntimeException {
    final String message;
    public ApiAccessException( String message){
        super(message);
        this.message = message;
    }
}
