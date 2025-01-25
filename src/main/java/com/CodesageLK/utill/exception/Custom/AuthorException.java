package com.CodesageLK.utill.exception.Custom;

import com.CodesageLK.utill.exception.SuperException;

public class AuthorException extends SuperException {
    public AuthorException() {
    }

    public AuthorException(String message) {
        super(message);
    }

    public AuthorException(String message, Throwable cause) {
        super(message, cause);
    }
}
