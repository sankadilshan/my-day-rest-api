package com.sankadilshan.myday.exception;

import com.sankadilshan.myday.utils.StringUtil;

public class BadCredentialException extends RuntimeException{

    private final static String message = "Bad credential!, check your credential again username: %s, password: %s";
    public BadCredentialException(String usernme, String password) {
        super(StringUtil.stringFormatter(message, usernme, password));
    }
}
