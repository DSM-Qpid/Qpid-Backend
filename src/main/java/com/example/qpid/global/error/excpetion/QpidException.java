package com.example.qpid.global.error.excpetion;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QpidException extends RuntimeException {
    private final ErrorCode errorCode;
}
