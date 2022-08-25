package es.eoi.java2022.recuerdamelon.dto.service.mapper;

import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserRoleServiceMapper.class})
public interface UserServiceMapper extends IEntityMapper<User, UserDTO> {
}
