package es.eoi.java2022.recuerdamelon.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter DATE_FORMATER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String dateToString(ZonedDateTime date) {
        return date != null ? date.format(DATE_FORMATER) : null;
    }

    public static ZonedDateTime stringToDate(String stringDate) {
        return ZonedDateTime.parse(stringDate, DATE_FORMATER.withZone(ZoneId.of("UTC")));
    }

    public static ZonedDateTime timeStamp (Timestamp timestamp){
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return zonedDateTime;
    }
}

