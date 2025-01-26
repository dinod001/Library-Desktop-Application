package com.CodesageLK.utill.exception.Custom;

import com.CodesageLK.utill.exception.SuperException;

public class CategoryException extends SuperException {
    public CategoryException() {
    }

    public CategoryException(String message) {
        super(message);
    }

    public CategoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
