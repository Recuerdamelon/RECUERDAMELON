package es.eoi.java2022.recuerdamelon.dto.service.mapper;

import es.eoi.java2022.recuerdamelon.dto.MessagesDTO;
import es.eoi.java2022.recuerdamelon.data.entity.ChatMessage;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {MessagesServiceMapper.class})
public interface MessagesServiceMapper extends IEntityMapper<ChatMessage, MessagesDTO> {
}
