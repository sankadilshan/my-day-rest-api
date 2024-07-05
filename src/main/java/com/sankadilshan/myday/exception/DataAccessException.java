package com.sankadilshan.myday.exception;

import com.sankadilshan.myday.utils.StringUtil;

public class DataAccessException extends RuntimeException {
    private final static String message = "Unable to access data at the time";
    public DataAccessException() {
        super(StringUtil.stringFormatter(message));
    }
}
