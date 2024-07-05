package com.sankadilshan.myday.exception;

import com.sankadilshan.myday.utils.StringUtil;

public class InvalidFirstNameException extends RuntimeException{

        private final static String message = "Invalid FirstName: %1$s.";
        public InvalidFirstNameException(String name) {
            super(StringUtil.stringFormatter(message, name));
        }
}
