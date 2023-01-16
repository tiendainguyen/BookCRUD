package com.book.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE_INTEGER_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMđd");
    private static final String VI_ZONE = "Asia/Ho_Chi_Minh";
    public static LocalDateTime getCurrentDateTime () {
        return LocalDateTime.now(ZoneId.of(VI_ZONE));
    }
    public static String getCurrenDateTimeStr(){
        return getCurrentDateTime().format(DATE_TIME_FORMATTER);
    }
}
