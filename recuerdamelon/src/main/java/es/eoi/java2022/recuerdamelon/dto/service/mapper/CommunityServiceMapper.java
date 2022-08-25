package es.eoi.java2022.recuerdamelon.dto.service.mapper;

import es.eoi.java2022.recuerdamelon.data.entity.Community;
import es.eoi.java2022.recuerdamelon.dto.CommunityDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommunityServiceMapper extends IEntityMapper <Community, CommunityDTO>{
}
