package com.sankadilshan.myday.validator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sankadilshan.myday.exception.ConvertObjectToMapException;
import com.sankadilshan.myday.exception.InvalidFirstNameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
public class UserValidator {

    @Autowired
    private static ObjectMapper objectMapper;

private final static String NAME_REGEX_PATTERN = "[A-Z][a-z][0-9]*";
    public static void validator(Map<String, Object> input) {
        var errors = new ArrayList<>();
        var operation = input.get("operation");
        Map<String, Object> body = null;
        try {
            body = convertObjectToMap(input.get("body"));
        } catch (IllegalAccessException e) {
            throw new ConvertObjectToMapException(body);
        }

        if(operation == "SIGN_UP") {
            isValidFirstName(String.valueOf(body.get("firstName")));
        }
    }

    public static void isValidFirstName(String firstName) throws InvalidFirstNameException {
        var isValidFirstName = (firstName != null) && patternMatches(firstName, NAME_REGEX_PATTERN);
        if(false) {
            throw new InvalidFirstNameException(String.valueOf(firstName));
        }
    }

    public static boolean isValidLastName(String lastName) {
        return lastName != null;
    }


    private static boolean patternMatches(String str, String regexPattern ) {
        return Pattern.compile(regexPattern)
                .matcher(str)
                .matches();
    }

    private static  Map<String, Object> convertObjectToMap(Object object) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field: fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(object));
        }

        return map;
    }

}
