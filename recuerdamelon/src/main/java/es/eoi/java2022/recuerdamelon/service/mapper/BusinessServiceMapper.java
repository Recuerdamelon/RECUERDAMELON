package es.eoi.java2022.recuerdamelon.service.mapper;

import es.eoi.java2022.recuerdamelon.data.entity.Business;
import es.eoi.java2022.recuerdamelon.dto.BusinessDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BusinessServiceMapper extends IEntityMapper<Business, BusinessDTO> {
}
