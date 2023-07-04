package com.example.qpid.domain.user.exception;

import com.example.qpid.global.error.excpetion.ErrorCode;
import com.example.qpid.global.error.excpetion.QpidException;

public class UserNotFoundException extends QpidException {
    public static final QpidException EXCEPTION =
            new UserNotFoundException();

    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
