package com.sankadilshan.myday.exception;

import com.sankadilshan.myday.utils.StringUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNameNotFoundException extends RuntimeException {

    private final static String message = "Unable to find valid user for given username, %s";
    public UserNameNotFoundException(String name) {
        super(StringUtil.stringFormatter(message, name));
    }
}
