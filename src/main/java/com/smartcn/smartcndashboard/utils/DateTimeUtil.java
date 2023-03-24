package com.smartcn.smartcndashboard.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static String getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(dateFormatter);
    }

    public static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(timeFormatter);
    }

}
