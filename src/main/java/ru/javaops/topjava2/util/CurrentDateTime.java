package ru.javaops.topjava2.util;

import lombok.experimental.UtilityClass;

import java.time.LocalTime;

@UtilityClass
public class CurrentDateTime {
    public static LocalTime getCurrentTime() {
        return LocalTime.now();
    }
}