package com.bookstoe_project.exception;

public class NoAccountFound extends RuntimeException {
    public NoAccountFound(String message) {
        super(message);
    }
}
