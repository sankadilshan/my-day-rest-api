package com.sankadilshan.myday.exception;

import com.sankadilshan.myday.utils.StringUtil;

public class InvalidUserNameException extends RuntimeException {
    private final static String message = "Invalid username or already exists, %s.";
    public InvalidUserNameException(String name) {
        super(StringUtil.stringFormatter(message, name));
    }

}
