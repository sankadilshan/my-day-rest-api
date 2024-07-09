package com.sankadilshan.myday.validator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sankadilshan.myday.exception.ConvertObjectToMapException;
import com.sankadilshan.myday.exception.UserValidationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
public class UserValidator {

    private final static String NAME_REGEX_PATTERN = "[A-Z][a-z][0-9]*";
    private final static String EMAIL_REGEX_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@ [^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    @Autowired
    private static ObjectMapper objectMapper;

    public static void validator(Map<String, Object> input) {
        var errors = new ArrayList<>();
        var operation = input.get("operation");
        Map<String, Object> body = null;
        try {
            body = convertObjectToMap(input.get("body"));
        } catch (IllegalAccessException e) {
            throw new ConvertObjectToMapException(body);
        }

        if (operation == "SIGN_UP") {
            isValidFirstName(String.valueOf(body.get("firstName")));
            isValidLastName(String.valueOf(body.get("lastName")));
            isValidUsername(String.valueOf(body.get("email")));
            isValidPassword(String.valueOf(body.get("password")));
        }
    }

    private static void isValidPassword(String password) {
    }

    private static void isValidUsername(String email) {
        boolean isValidUsername = email != null && patternMatches(email, EMAIL_REGEX_PATTERN);
        if (!isValidUsername) {
            throw new UserValidationFailedException(String.valueOf(email), "email");
        }
    }

    public static void isValidFirstName(String firstName) throws UserValidationFailedException {
        var isValidFirstName = (firstName != null) && patternMatches(firstName, NAME_REGEX_PATTERN);
        if (isValidFirstName) {
            throw new UserValidationFailedException(firstName, "firstname");
        }
    }

    public static void isValidLastName(String lastName) {
        var isValidLastName = (lastName != null) && patternMatches(lastName, NAME_REGEX_PATTERN);
        if (isValidLastName) {
            throw new UserValidationFailedException(lastName, "lastname");
        }
    }


    private static boolean patternMatches(String str, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(str).matches();
    }

    private static Map<String, Object> convertObjectToMap(Object object) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(object));
        }

        return map;
    }

}
