package es.eoi.java2022.recuerdamelon.utils;

import es.eoi.java2022.recuerdamelon.data.entity.UserRole;
import es.eoi.java2022.recuerdamelon.dto.UserRoleDTO;
import es.eoi.java2022.recuerdamelon.service.UserRoleService;
import es.eoi.java2022.recuerdamelon.service.mapper.UserRoleServiceMapper;

import java.util.ArrayList;
import java.util.List;

public class SetRoleToUser {


    public static List<UserRoleDTO> setRole (Integer rolId, UserRoleService service, UserRoleServiceMapper mapper){
    List<UserRole> roles = new ArrayList<>();
    List<UserRoleDTO> rolesDto = new ArrayList<>();
            roles.add(service.findById(rolId));
            for (UserRole role:roles) {
        rolesDto.add(mapper.toDto(role));
    }
            return rolesDto;
    }
}
