package es.eoi.java2022.recuerdamelon.event;

import es.eoi.java2022.recuerdamelon.data.entity.Notification;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class NotificationEvent extends ApplicationEvent {
    private static final long serialVersionUID = -3762610544324295353L;
    private final List<Notification> notificationList;

    public NotificationEvent(Object source, String message, List<Notification> notificationList) {
        super(source);
        this.notificationList = notificationList;

    }
    public List<Notification> getNotificationList() {
        return notificationList;
    }

}