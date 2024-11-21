package com.example.CNFABackend.Exceptions;

public class LockedException extends RuntimeException {
    public LockedException(String message) {
        super(message);
    }
}
