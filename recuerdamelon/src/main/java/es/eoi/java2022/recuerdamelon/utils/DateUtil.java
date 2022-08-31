package es.eoi.java2022.recuerdamelon.utils;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter DATE_FORMATER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATER1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static String dateToString(ZonedDateTime date) {
        return date != null ? date.format(DATE_FORMATER) : null;
    }

    public static ZonedDateTime stringToDate(String stringDate) {
        return ZonedDateTime.parse(stringDate, DATE_FORMATER.withZone(ZoneId.of("UTC")));
    }

    public static LocalDateTime stringToDate1(String stringDate) {
        int pos = 10;
        char th=' ';
        String toParse = stringDate.substring(0,pos) + th + stringDate.substring(pos+1) ;
        System.out.println("toParse = " + toParse);
        return LocalDateTime.parse(toParse, DATE_FORMATER1);
    }

    public static ZonedDateTime timeStamp (Timestamp timestamp){
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return zonedDateTime;
    }
    public static LocalDateTime dateTime(LocalDate date, LocalTime time){
        LocalDateTime localdatetime = LocalDateTime.of(date, time);
        return localdatetime;
    }
    public static ZonedDateTime localToZone(LocalDateTime dateTime){
        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.systemDefault());
        return  zonedDateTime;
    }
    public static String dateToString1(LocalDateTime date) {return date != null ? date.format(DATE_FORMATER) : null;}

}

