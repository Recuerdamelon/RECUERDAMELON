package es.eoi.java2022.recuerdamelon.service;

import es.eoi.java2022.recuerdamelon.dto.MessagesDTO;
import es.eoi.java2022.recuerdamelon.event.MessageEvent;
import es.eoi.java2022.recuerdamelon.event.MessageEventSaludo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class PublicarMensaje {
    private static final Logger logger = LoggerFactory.getLogger(PublicarMensaje.class);

    private final ApplicationEventPublisher publisher;

    public PublicarMensaje(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void  EnviarMensajeEvento(String mensaje){
        logger.info("Inicio el envío de mensaje por eventos");
        MessageEvent messageEvent = new MessageEvent(this,mensaje);
        publisher.publishEvent(messageEvent);
        logger.info("Evento generado");

    }
    public void  EnviarMensajeSaludo(MessagesDTO messagesDTO){
        logger.info("Inicio el envío de mensaje saludo por eventos");
        MessageEventSaludo messageEventSaludo = new MessageEventSaludo(this, messagesDTO);
        publisher.publishEvent(messageEventSaludo);
        logger.info("Evento generado de saludo");

    }

}
