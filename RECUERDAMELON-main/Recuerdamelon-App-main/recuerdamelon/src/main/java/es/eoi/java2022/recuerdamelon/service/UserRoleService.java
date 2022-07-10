package es.eoi.java2022.recuerdamelon.service;

import es.eoi.java2022.recuerdamelon.data.entity.UserRole;
import es.eoi.java2022.recuerdamelon.data.repository.UserRoleRepository;
import es.eoi.java2022.recuerdamelon.dto.UserRoleDTO;
import es.eoi.java2022.recuerdamelon.service.mapper.UserRoleServiceMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserRoleService {
    private final UserRoleRepository repository;
    private final UserRoleServiceMapper mapper;

    public UserRoleService(UserRoleRepository repository, UserRoleServiceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<UserRole> findAll(Pageable pageable) {
        return repository.findAll(pageable).toList();
    }

    public UserRole findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("El Id %d no existe", id)));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    public UserRole update(UserRole userRole) {
        if (userRole.getId() == null){
            throw new RuntimeException("No se puede actualizar tareas que no tengan Identificador");
        }
        return repository.save(userRole);
    }
    public UserRoleDTO save(UserRoleDTO userRoleDTO) {
        if (userRoleDTO.getId() != null){
            throw new RuntimeException("El Identificador no puede ser nulo");
        }
        return mapper.toDto(repository.save(mapper.toEntity(userRoleDTO)));
    }

}