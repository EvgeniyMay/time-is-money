package com.myLearning.timeIsMoney.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateUtil {

    public static LocalDateTime parseHtmlDate(String htmlInputData) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        return LocalDateTime.parse(htmlInputData, formatter);
    }

}
