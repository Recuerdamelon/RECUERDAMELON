package es.eoi.java2022.recuerdamelon.service;

import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.data.repository.UserRepository;
import es.eoi.java2022.recuerdamelon.dto.UserDTO;
import es.eoi.java2022.recuerdamelon.service.mapper.UserServiceMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    private final UserRepository repository;
    private final UserServiceMapper mapper;

    public UserService(UserRepository repository, UserServiceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<User> findAll(Pageable pageable) {
        return repository.findAll(pageable).toList();

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
    public UserDTO save(UserDTO userDTO) {
        if (userDTO.getId() != null){
            throw new RuntimeException("El Identificador no puede ser nulo");
        }
        return mapper.toDto(repository.save(mapper.toEntity(userDTO)));
    }

}