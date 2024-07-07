package com.sankadilshan.myday.exception;

import com.sankadilshan.myday.utils.StringUtil;
import org.springframework.security.core.AuthenticationException;

public class UserTokenExpiredException extends AuthenticationException {

    private final static String message = "your token expired, please resign again, token: %1$s";

    public UserTokenExpiredException(String token, Throwable e) {
        super(StringUtil.stringFormatter(message, token), e);
    }
}
