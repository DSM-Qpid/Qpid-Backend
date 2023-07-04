package com.example.qpid.global.error.excpetion;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    USER_ALREADY_EXISTS(409, "User Already Exists"),

    INTERNAL_SERVER_SERROR(500, "Internal Server Error");

    private final int status;
    private final String message;
}
