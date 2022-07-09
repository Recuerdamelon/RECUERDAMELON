package es.eoi.java2022.recuerdamelon.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

public class CalendarDTO implements Serializable {
    private Integer id;
    private String name;

    private String taskDate;

    private List<TaskDTO> task;



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

    public List<TaskDTO> getTask() {
        return task;
    }

    public void setTask(List<TaskDTO> task) {
        this.task = task;
    }
}
