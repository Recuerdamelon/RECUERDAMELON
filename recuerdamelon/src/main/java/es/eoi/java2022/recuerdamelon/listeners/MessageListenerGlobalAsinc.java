/*package es.eoi.java2022.recuerdamelon.listeners;

import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.event.MessageEvent;
import es.eoi.java2022.recuerdamelon.event.MessageEventSaludo;
import es.eoi.java2022.recuerdamelon.service.MessagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageListenerGlobalAsinc {
    private static final Logger logger = LoggerFactory.getLogger(MessageListenerGlobalAsinc.class);
    private final MessagesService service;

    public MessageListenerGlobalAsinc(MessagesService service) {
        this.service = service;
    }

    @EventListener
    @Async
    public void EscucharSaludoAsinc(MessageEventSaludo event) {
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        logger.info("El usuario : " + user.getUsername()  + " ha recibido el mensaje(MessageEventSaludo) asincrono es: " + event.getMessage().getMessage() + ", viene de:" + event.getSource() +
                " y ha llegado a la hora:" + event.getTimestamp());
        //Grabar mensaje para el que envía
        this.service.save(event.getMessage());
        //Grabar mensaje para el que recibe
        this.service.save(event.getMessage(),1);
    }
    @EventListener
    @Async
    public void EscucharMensajeAsinc(MessageEvent eventmensaje) {

        logger.info("El mensaje(MessageEvent) asincrono es: " + eventmensaje.getMessage() + ", viene de:" + eventmensaje.getSource() +
                " y ha llegado a la hora:" + eventmensaje.getTimestamp());

    }
}*/
