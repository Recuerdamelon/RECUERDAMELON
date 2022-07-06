package es.eoi.java2022.recuerdamelon.service.mapper;

import es.eoi.java2022.recuerdamelon.data.entity.Notification;
import es.eoi.java2022.recuerdamelon.dto.NotificationDTO;
import es.eoi.java2022.recuerdamelon.utils.DateUtil;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceMapper extends AbstractServiceMapper<Notification, NotificationDTO> {
    public Notification toEntity(NotificationDTO dto) {
        final Notification entity = new Notification();
        entity.setId(dto.getId());
        entity.setNotificationTime(dto.getNotificationTime());
        entity.setNotified(dto.isNotified());
        return entity;
    }
    public NotificationDTO toDto(Notification entity) {
        final NotificationDTO dto = new NotificationDTO();
        dto.setId(entity.getId());
        dto.setNotificationTime(entity.getNotificationTime());
        dto.setNotified(entity.isNotified());
        return dto;
    }
}
