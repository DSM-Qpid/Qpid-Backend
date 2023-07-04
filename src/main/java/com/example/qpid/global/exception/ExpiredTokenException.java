package com.example.qpid.global.exception;

import com.example.qpid.global.error.excpetion.ErrorCode;
import com.example.qpid.global.error.excpetion.QpidException;

public class ExpiredTokenException extends QpidException {

    public static QpidException EXCEPTION = new ExpiredTokenException();

    private ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
