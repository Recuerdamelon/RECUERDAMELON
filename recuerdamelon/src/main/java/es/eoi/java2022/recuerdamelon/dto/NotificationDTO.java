package es.eoi.java2022.recuerdamelon.dto;


import java.util.List;

public class NotificationDTO {
    private Integer id;
    private Integer notificationTime;
    private boolean notified;

    private List<UserDTO> user;

    private List<TaskDTO> task;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(Integer notificationTime) {
        this.notificationTime = notificationTime;
    }

    public boolean isNotified() {
        return notified;
    }

    public void setNotified(boolean notified) {
        this.notified = notified;
    }

    public List<UserDTO> getUser() {
        return user;
    }

    public void setUser(List<UserDTO> user) {
        this.user = user;
    }

    public List<TaskDTO> getTask() {
        return task;
    }

    public void setTask(List<TaskDTO> task) {
        this.task = task;
    }
}
