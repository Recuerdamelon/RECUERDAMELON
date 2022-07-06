package es.eoi.java2022.recuerdamelon.dto;



public class NotificationDTO {
    private Integer id;
    private Integer notificationTime;
    private boolean notified;

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
}
