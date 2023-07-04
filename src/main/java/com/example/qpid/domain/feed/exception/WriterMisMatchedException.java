package com.example.qpid.domain.feed.exception;

import com.example.qpid.global.error.excpetion.ErrorCode;
import com.example.qpid.global.error.excpetion.QpidException;

public class WriterMisMatchedException extends QpidException {
    public static final QpidException EXCEPTION =
            new WriterMisMatchedException();

    private WriterMisMatchedException() {
        super(ErrorCode.WRITER_MIS_MATCHED);
    }
}
