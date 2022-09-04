package es.eoi.java2022.recuerdamelon.dto;

import es.eoi.java2022.recuerdamelon.data.entity.Community;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.List;

public class HorarioDTO {
    private String title;

    private List<Community> equipos;

    private List<String> task;

    private List<User> friends;

    private List<String> startLocalDateTime;


    private List<String> endLocalDateTime;


//    private LocalDate start;
//    private LocalDate end;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Community> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Community> equipos) {
        this.equipos = equipos;
    }

    public List<String> getTask() {
        return task;
    }

    public void setTask(List<String> task) {
        this.task = task;
    }
    
    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<String> getStartLocalDateTime() {
        return startLocalDateTime;
    }

    public void setStartLocalDateTime(List<String> startLocalDateTime) {
        this.startLocalDateTime = startLocalDateTime;
    }

    public List<String> getEndLocalDateTime() {
        return endLocalDateTime;
    }

    public void setEndLocalDateTime(List<String> endLocalDateTime) {
        this.endLocalDateTime = endLocalDateTime;
    }
}
