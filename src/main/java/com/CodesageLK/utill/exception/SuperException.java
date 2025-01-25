package com.CodesageLK.utill.exception;

public class SuperException extends Exception {
    public SuperException() {
    }

    public SuperException(String message) {
        super(message);
    }

    public SuperException(String message, Throwable cause) {
        super(message, cause);
    }
}
