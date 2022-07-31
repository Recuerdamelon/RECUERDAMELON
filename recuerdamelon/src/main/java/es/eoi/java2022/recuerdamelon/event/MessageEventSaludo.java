package es.eoi.java2022.recuerdamelon.event;

import es.eoi.java2022.recuerdamelon.dto.MessagesDTO;
import org.springframework.context.ApplicationEvent;

public class MessageEventSaludo extends ApplicationEvent {
    private static final long serialVersionUID = -3762610544324295353L;
    private final MessagesDTO messagesDTO;

    public MessageEventSaludo(Object source, MessagesDTO messagesDTO) {
        super(source);
        this.messagesDTO = messagesDTO;
    }
    public MessagesDTO getMessage() {
        return messagesDTO;
    }
}
