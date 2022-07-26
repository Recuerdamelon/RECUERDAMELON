package es.eoi.java2022.recuerdamelon.service.mapper;

import es.eoi.java2022.recuerdamelon.data.entity.BusinessUser;
import es.eoi.java2022.recuerdamelon.dto.BusinessUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BusinessUserServiceMapper extends IEntityMapper<BusinessUser, BusinessUserDTO>{
}
