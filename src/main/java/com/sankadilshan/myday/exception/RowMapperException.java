package com.sankadilshan.myday.exception;

import com.sankadilshan.myday.utils.StringUtil;

public class RowMapperException extends RuntimeException{
    private final static String message = "Unable to map the query result set with class :: %s.";
    public RowMapperException(String name) {
        super(StringUtil.stringFormatter(message, name));
    }
}
