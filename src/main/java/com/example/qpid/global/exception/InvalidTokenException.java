package com.example.qpid.global.exception;

import com.example.qpid.global.error.excpetion.ErrorCode;
import com.example.qpid.global.error.excpetion.QpidException;

public class InvalidTokenException extends QpidException {

    public static QpidException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
