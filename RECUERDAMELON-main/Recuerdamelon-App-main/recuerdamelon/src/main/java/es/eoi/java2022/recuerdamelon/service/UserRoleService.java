package es.eoi.java2022.recuerdamelon.service;

import es.eoi.java2022.recuerdamelon.data.entity.UserRole;
import es.eoi.java2022.recuerdamelon.data.repository.UserRoleRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserRoleService {
    private final UserRoleRepository repository;

    public UserRoleService(UserRoleRepository repository) {
        this.repository = repository;
    }

    public List<UserRole> findAll(PageRequest of) {
        return repository.findAll();

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
    public UserRole save(UserRole userRole) {
        if (userRole.getId() != null){
            throw new RuntimeException("El Identificador no puede ser nulo");
        }
        return repository.save(userRole);
    }

}