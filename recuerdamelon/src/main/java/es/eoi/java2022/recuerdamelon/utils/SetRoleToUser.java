package es.eoi.java2022.recuerdamelon.utils;

import es.eoi.java2022.recuerdamelon.data.entity.UserRole;
import es.eoi.java2022.recuerdamelon.dto.UserRoleDTO;
import es.eoi.java2022.recuerdamelon.service.UserRoleService;
import es.eoi.java2022.recuerdamelon.service.mapper.UserRoleServiceMapper;

import java.util.ArrayList;
import java.util.List;

public class SetRoleToUser {

    static UserRoleService userRoleService;
    static UserRoleServiceMapper userRoleServiceMapper;

    public SetRoleToUser(UserRoleService userRoleService, UserRoleServiceMapper userRoleServiceMapper) {
        this.userRoleService = userRoleService;
        this.userRoleServiceMapper = userRoleServiceMapper;
    }

    public static List<UserRoleDTO> setRole (Integer rolId){
    List<UserRole> roles = new ArrayList<>();
    List<UserRoleDTO> rolesDto = new ArrayList<>();
            roles.add(userRoleService.findById(rolId));
            for (UserRole role:roles) {
        rolesDto.add(userRoleServiceMapper.toDto(role));
    }
            return rolesDto;
    }
}
