package es.eoi.java2022.recuerdamelon.utils;

import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.dto.MensajesDTO;
import es.eoi.java2022.recuerdamelon.service.MensajesService;
import es.eoi.java2022.recuerdamelon.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Invitation {

    public static void makeInvitation(List<String> usernames, String communityName, User admin,
                                             List<User> recieveInvitation, UserService service, MensajesService save) {
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        MensajesDTO invitation = new MensajesDTO();
        System.out.println("AAAAAAAAAAAAAAY" + recieveInvitation.size());
        for (String invited : usernames) {
            List<User> recieve = new ArrayList<>();
            recieve.add(service.findByUsername(invited));
            invitation.setUserId(user.getId());
            invitation.setUsers(recieve);
            invitation.setDate(DateUtil.dateToString(zonedDateTime));
            invitation.setInvitation(true);
            invitation.setMensaje("El usuario " + admin.getUsername() + "  te ha enviado una invitacion a la comunidad" +
                    " de meloners " + communityName);
            invitation.setTitle("Invitación a comunidad");
            invitation.setSender(user.getUsername());
            invitation.setCommunity(communityName);
            save.save(invitation);
        }
    }
}


