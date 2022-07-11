package es.eoi.java2022.recuerdamelon.data.entity;

import javax.persistence.*;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer notificationTime;

    @Column(nullable = false)
    private boolean notified;

    /*  notification <--> task  */
    @ManyToOne
    private Task task;

    /*  notification <--> user  */
    @ManyToOne
    private User user;


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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}



