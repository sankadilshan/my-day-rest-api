package com.sankadilshan.myday.enums;

public enum CustomHttpStatus{


    FIRSTNAME_INVALID_ERROR(600,"Invalid FirstName"),
    LASTNAME_INVALID_ERROR(601, "Invalid Lastname"),
    EMAIL_INVALID_ERROR(602, "Invalid Email Address"),

    DB_EXPENSE_DATA_FETCH_ERROR(700, "Failed to fetch expense data"),

    USER_UNAUTHORIZED(401, "User Unauthorized");

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
