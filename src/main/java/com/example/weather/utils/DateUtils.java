package com.example.weather.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    /**
     * extracts tokens from the file name
     * @param fileName e.g. IDY03000.202411121300.axf
     * @return the extracted local date time object
     */
    public static LocalDateTime getFileDateTime(String fileName) {
        String dateTimeString = fileName.split("\\.")[1];
        return LocalDateTime.parse(dateTimeString, formatter);
    }

}
