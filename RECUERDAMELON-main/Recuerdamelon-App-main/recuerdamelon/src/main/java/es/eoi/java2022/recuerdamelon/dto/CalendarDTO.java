package es.eoi.java2022.recuerdamelon.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class CalendarDTO implements Serializable {
    private Integer id;
    private String name;

    private String taskDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }
}
