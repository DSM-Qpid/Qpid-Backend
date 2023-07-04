package com.example.qpid.domain.feed.exception;

import com.example.qpid.global.error.excpetion.ErrorCode;
import com.example.qpid.global.error.excpetion.QpidException;

public class SearchNotFoundException extends QpidException {
    public static final QpidException EXCEPTION =
            new SearchNotFoundException();

    private SearchNotFoundException() {
        super(ErrorCode.SEARCH_NOT_FOUND);
    }
}
