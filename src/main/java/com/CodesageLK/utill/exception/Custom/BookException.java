package com.CodesageLK.utill.exception.Custom;

import com.CodesageLK.utill.exception.SuperException;

public class BookException extends SuperException {
    public BookException() {
    }

    public BookException(String message) {
        super(message);
    }

    public BookException(String message, Throwable cause) {
        super(message, cause);
    }
}
