package com.sankadilshan.myday.exception;

import com.sankadilshan.myday.enums.CustomHttpStatus;
import com.sankadilshan.myday.utils.StringUtil;
import lombok.Getter;


public class UserValidationFailedException extends RuntimeException {

    @Getter
    private CustomHttpStatus customHttpStatus;
    private String value;
    private String field;

    private final static String FIRSTNAME_INVALID_MESSAGE = "Invalid FirstName: %1$s.";
    private final static String LASTNAME_INVALID_MESSAGE = "Invalid LastName: %1$s.";
    private final static String EMAIL_INVALID_MESSAGE = "Invalid Username: %1$s.";

    public UserValidationFailedException(String value, String field) {
        this.value = value;
        this.field = field;
    }

    @Override
    public String getMessage() {
        String message = null;
        CustomHttpStatus status = null;
        switch (field) {
            case "email":
                message = EMAIL_INVALID_MESSAGE;
                status = CustomHttpStatus.FIRSTNAME_INVALID_ERROR;
                break;
            case "firstName":
                message = FIRSTNAME_INVALID_MESSAGE;
                status = CustomHttpStatus.LASTNAME_INVALID_ERROR;
                break;
            case "lastName":
                message = LASTNAME_INVALID_MESSAGE;
                status = CustomHttpStatus.EMAIL_INVALID_ERROR;
                break;
        }

        this.customHttpStatus = status;
        return StringUtil.stringFormatter(message, value).concat(" Please try again with correct inputs.");

    }
}
