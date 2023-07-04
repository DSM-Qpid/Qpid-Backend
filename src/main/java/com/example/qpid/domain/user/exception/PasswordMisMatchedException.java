package com.example.qpid.domain.user.exception;

import com.example.qpid.global.error.excpetion.ErrorCode;
import com.example.qpid.global.error.excpetion.QpidException;

public class PasswordMisMatchedException extends QpidException {
    public static final QpidException EXCEPTION =
            new PasswordMisMatchedException();

    private PasswordMisMatchedException() {
        super(ErrorCode.PASSWORD_MIS_MATCHED);
    }
}
