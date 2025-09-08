package com.subhrajit.exceptions;

public class OpenForumException extends RuntimeException {

    public OpenForumException(String message) {
        super(message);
    }

    public OpenForumException(String message, Exception exception) {
        super(message, exception);
    }
}

