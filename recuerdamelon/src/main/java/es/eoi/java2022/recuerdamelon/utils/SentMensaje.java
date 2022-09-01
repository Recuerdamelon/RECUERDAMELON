package es.eoi.java2022.recuerdamelon.utils;

import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.dto.MensajesDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SentMensaje {

    public static MensajesDTO SentRecord (User user, MensajesDTO dto, List<User> recievers){
        MensajesDTO dtoSender = new MensajesDTO();
        dtoSender.setMensaje(dto.getMensaje());
        dtoSender.setSent(true);
        dtoSender.setTitle(dto.getTitle());
        dto.setSender(user.getUsername());
        dtoSender.setUserId(user.getId());
        List<User> sender = new ArrayList<>();
        sender.add(user);
        dtoSender.setUsers(sender);
        dtoSender.setDate(dto.getDate());
        String sendToo = (Arrays.toString(recievers.toArray()));
        dtoSender.setSender(sendToo);
        return dtoSender;
    }
}
