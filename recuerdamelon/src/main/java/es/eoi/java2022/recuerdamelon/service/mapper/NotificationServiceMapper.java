package es.eoi.java2022.recuerdamelon.service.mapper;

import es.eoi.java2022.recuerdamelon.data.entity.Notification;
import es.eoi.java2022.recuerdamelon.dto.NotificationDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface NotificationServiceMapper extends IEntityMapper<Notification,NotificationDTO>{

}
