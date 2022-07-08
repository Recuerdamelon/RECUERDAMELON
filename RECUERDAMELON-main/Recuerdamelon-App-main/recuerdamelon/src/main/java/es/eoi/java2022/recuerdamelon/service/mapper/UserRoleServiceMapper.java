package es.eoi.java2022.recuerdamelon.service.mapper;

import es.eoi.java2022.recuerdamelon.data.entity.UserRole;
import es.eoi.java2022.recuerdamelon.dto.UserRoleDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
public interface UserRoleServiceMapper extends IEntityMapper<UserRole, UserRoleDTO> {
}
