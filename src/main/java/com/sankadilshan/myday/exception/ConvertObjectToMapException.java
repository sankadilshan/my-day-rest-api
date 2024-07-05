package com.sankadilshan.myday.exception;

import com.sankadilshan.myday.utils.StringUtil;

public class ConvertObjectToMapException extends RuntimeException {

    private final static String message = "Illegal exception unable to convert instance object to map, %s";

    public ConvertObjectToMapException( Object object) {
        super(StringUtil.stringFormatter(message, String.valueOf(object)));
    }
}
