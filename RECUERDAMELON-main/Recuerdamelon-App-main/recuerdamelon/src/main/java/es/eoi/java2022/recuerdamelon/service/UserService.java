package es.eoi.java2022.recuerdamelon.service;

import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.data.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll(PageRequest of) {
        return repository.findAll();

    }

    public User findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("El Id %d no existe", id)));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    public User update(User user) {
        if (user.getId() == null){
            throw new RuntimeException("No se puede actualizar tareas que no tengan Identificador");
        }
        return repository.save(user);
    }
    public User save(User user) {
        if (user.getId() != null){
            throw new RuntimeException("El Identificador no puede ser nulo");
        }
        return repository.save(user);
    }

}