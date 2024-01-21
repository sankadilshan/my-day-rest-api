package com.sankadilshan.myday.utils;

public class StringUtil {

    public static String stringFormatter(String msg, String... value){
        return String.format(msg, value);
    }
}
