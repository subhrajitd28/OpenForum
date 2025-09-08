package com.subhrajit.exceptions;

public class SubforumNotFoundException extends RuntimeException {
    public SubforumNotFoundException(String message) {
        super(message);
    }
}