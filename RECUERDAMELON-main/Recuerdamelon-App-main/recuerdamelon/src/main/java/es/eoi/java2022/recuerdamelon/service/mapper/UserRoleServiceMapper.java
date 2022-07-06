package es.eoi.java2022.recuerdamelon.service.mapper;

import es.eoi.java2022.recuerdamelon.data.entity.UserRole;
import es.eoi.java2022.recuerdamelon.dto.UserRoleDTO;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceMapper extends AbstractServiceMapper<UserRole, UserRoleDTO> {

    public UserRole toEntity(UserRoleDTO dto) {
        final UserRole entity = new UserRole();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }

    public UserRoleDTO toDto(UserRole entity) {
        final UserRoleDTO dto = new UserRoleDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}
