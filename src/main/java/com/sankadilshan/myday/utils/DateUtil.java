package com.sankadilshan.myday.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.DateFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    private final static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static LocalDate dateFormatter(String dStr, String pattern) {
        return LocalDate.parse(dStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime dateTimeFormatter(String dStr, String pattern) {
        DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dStr, dateTimeFormatter);
    }

    public static Date addMinutes(int minutes){
        Date date =  new Date();
        date.setMinutes(date.getMinutes() +  minutes);
        return date;
    }

    public static class DateConstant {
        public final static String YYYYMMDD_FORMAT= "yyyy-MM-dd";
        public final static String YYYYMMDDHHMMSS_FORMAT= "yyyy-MM-dd HH:mm:ss";
    }

}
