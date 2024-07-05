package com.sankadilshan.myday.utils;

public enum CustomHttpStatus{


    FIRSTNAME_INVALID_ERROR(600,"Invalid FirstName"),
    LASTNAME_INVALID_ERROR(601, "Invalid Lastname"),
    EMAIL_INVALID_ERROR(602, "Invalid Email Address"),


    USER_UNATHORIZED(401, "User Unathorized");

    private int value;
    private String message;

    static {

    }
    CustomHttpStatus(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int value() {
        return this.value;
    }

}
