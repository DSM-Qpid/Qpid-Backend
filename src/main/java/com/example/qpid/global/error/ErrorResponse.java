package com.example.qpid.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class ErrorResponse {

    private final int status;
    private final String message;

    @Override
    public String toString() {
        return "{\n" + "\t\"status\": "
                + status + ",\t\"code\": \""
                + message + '\"' + "\n}";
    }
}