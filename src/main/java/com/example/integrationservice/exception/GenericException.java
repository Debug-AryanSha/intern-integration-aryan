package com.example.integrationservice.exception;

public class GenericException extends RuntimeException {
    public final String message;
    public GenericException(String message) {
        super(message);
        this.message = message;
    }
}
