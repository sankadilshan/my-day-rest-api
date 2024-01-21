package com.sankadilshan.myday.exception;


import com.sankadilshan.myday.utils.StringUtil;

public class JsonAccessAndProcessingException extends RuntimeException {
    private final static String message = "Unable to access or process with provided object class :: %s.";
    public JsonAccessAndProcessingException(String name) {
        super(StringUtil.stringFormatter(message, name));
    }

}
