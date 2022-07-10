package es.eoi.java2022.recuerdamelon.listeners;


import es.eoi.java2022.recuerdamelon.event.NotificationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener implements ApplicationListener<NotificationEvent> {
    private static final Logger logger = LoggerFactory.getLogger(NotificationListener.class);

    @Override
    public void onApplicationEvent(NotificationEvent event) {
      event.getNotificationList().forEach((eventNotification)->{
          logger.info("El usuario  " + eventNotification.getUser().getName()+ ", Te comparte la tarea con el titulo"
              + eventNotification.getTask().getTitle() + " y tiene un inicio" + eventNotification.getTask().getStartDate()
                  +" y tiene un final" +eventNotification.getTask().getEndDate());
      });

    }
}
