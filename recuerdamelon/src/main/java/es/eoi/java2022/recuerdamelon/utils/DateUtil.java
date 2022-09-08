package es.eoi.java2022.recuerdamelon.utils;

import es.eoi.java2022.recuerdamelon.data.entity.Task;
import es.eoi.java2022.recuerdamelon.service.TaskService;
import es.eoi.java2022.recuerdamelon.service.UserService;
import es.eoi.java2022.recuerdamelon.service.mapper.TaskServiceMapper;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        toParse = toParse + ":00";
        System.out.println("toParse = " + toParse);
        return LocalDateTime.parse(toParse, DATE_FORMATER);
    }

    public static LocalDateTime stringToDate2(String stringDate) {
        int pos = 10;
        char th=' ';
        String toParse = stringDate.substring(0,pos) + th + stringDate.substring(pos+1) ;
        System.out.println("toParse = " + toParse);
        return LocalDateTime.parse(toParse, DATE_FORMATER);
    }

    public static ZonedDateTime timeStamp (){
        Timestamp timestampNOW = new Timestamp(System.currentTimeMillis());
        LocalDateTime localDateTime = timestampNOW.toLocalDateTime();
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

    public static List<Task> setTasksOutOfDate (Timestamp timestamp, List<Task> all, TaskService service, TaskServiceMapper mapper){
        List<Task> endings = new ArrayList<>();
        for (Task task:all) {
            if(DateUtil.stringToDate2(task.getEndDate()).plusDays(1).isAfter(DateUtil.timeStamp().toLocalDateTime())){
                task.setDeleted(false);
                endings.add(task);
                service.save(mapper.toDto(task));
            }else{
                task.setDeleted(true);
                endings.add(task);
                service.save(mapper.toDto(task));
            }

        }
        return endings;

    }




}

