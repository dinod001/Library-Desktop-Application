package com.CodesageLK.utill.exception.Custom;

import com.CodesageLK.utill.exception.SuperException;

public class MemberCustomException extends SuperException {
    public MemberCustomException() {
    }

    public MemberCustomException(String message) {
        super(message);
    }

    public MemberCustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
