package es.eoi.java2022.recuerdamelon.service.mapper;

import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceMapper extends AbstractServiceMapper<User, UserDTO> {
    public User toEntity(UserDTO dto) {
        final User entity = new User();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setActive(dto.isActive());
        entity.setAvatar(dto.getAvatar());
        return entity;
    }

    public UserDTO toDto(User entity) {
        final UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setActive(entity.isActive());
        dto.setAvatar(entity.getAvatar());
        return dto;
    }
}
