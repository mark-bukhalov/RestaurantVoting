package ru.javaops.topjava2.util;

import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@UtilityClass
public class DateTimeUtil {
    private final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    public static Date stringToDate(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
