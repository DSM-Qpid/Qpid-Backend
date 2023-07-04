package com.example.qpid.global.error.excpetion;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    WRITER_MIS_MATCHED(400, "Writer MisMatched"),

    INVALID_TOKEN(401, "Invalid Token"),
    EXPIRED_TOKEN(401, "Expired Token"),

    USER_NOT_FOUND(404, "User Not Found"),
    FEED_NOT_FOUND(404, "Feed Not Found"),
    SEARCH_NOT_FOUND(404, "Search Not Found"),

    USER_ALREADY_EXISTS(409, "User Already Exists"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int status;
    private final String message;
}
