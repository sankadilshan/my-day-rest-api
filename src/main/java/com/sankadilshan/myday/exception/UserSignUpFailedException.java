package com.sankadilshan.myday.exception;

import com.sankadilshan.myday.utils.StringUtil;

public class UserSignUpFailedException extends RuntimeException {
    private final static String message = "Unable to signup the user at the time with the username %s";
    public UserSignUpFailedException(String name) {
        super(StringUtil.stringFormatter(message, name));
    }
}
