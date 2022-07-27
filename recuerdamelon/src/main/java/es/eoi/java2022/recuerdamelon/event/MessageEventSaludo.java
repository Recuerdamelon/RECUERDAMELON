package es.eoi.java2022.recuerdamelon.event;

import es.eoi.java2022.recuerdamelon.dto.MensajesDTO;
import org.springframework.context.ApplicationEvent;

public class MessageEventSaludo extends ApplicationEvent {
    private static final long serialVersionUID = -3762610544324295353L;
    private final MensajesDTO mensajesDTO;

    public MessageEventSaludo(Object source, MensajesDTO mensajesDTO) {
        super(source);
        this.mensajesDTO = mensajesDTO;
    }
    public MensajesDTO getMessage() {
        return mensajesDTO;
    }
}
