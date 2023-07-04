package com.example.qpid.domain.user.exception;

import com.example.qpid.global.error.excpetion.ErrorCode;
import com.example.qpid.global.error.excpetion.QpidException;

public class UserAlreadyExistException extends QpidException {
    public static final QpidException EXCEPTION =
            new UserAlreadyExistException();

    private UserAlreadyExistException() {
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}
