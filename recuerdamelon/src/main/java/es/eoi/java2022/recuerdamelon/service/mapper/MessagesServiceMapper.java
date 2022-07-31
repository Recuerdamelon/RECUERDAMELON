package es.eoi.java2022.recuerdamelon.service.mapper;

import es.eoi.java2022.recuerdamelon.data.entity.Message;
import es.eoi.java2022.recuerdamelon.dto.MessagesDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {MessagesServiceMapper.class})
public interface MessagesServiceMapper extends IEntityMapper<Message, MessagesDTO> {
}
