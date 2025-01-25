package com.CodesageLK.utill.exception.Custom;

import com.CodesageLK.utill.exception.SuperException;

public class PublisherException extends SuperException {
    public PublisherException() {
    }

    public PublisherException(String message) {
        super(message);
    }

    public PublisherException(String message, Throwable cause) {
        super(message, cause);
    }
}
