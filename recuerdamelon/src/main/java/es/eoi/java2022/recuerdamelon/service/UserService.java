package es.eoi.java2022.recuerdamelon.service;

import es.eoi.java2022.recuerdamelon.data.entity.Community;
import es.eoi.java2022.recuerdamelon.data.entity.Mensajes;
import es.eoi.java2022.recuerdamelon.data.entity.Task;
import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.data.repository.UserRepository;
import es.eoi.java2022.recuerdamelon.dto.UserDTO;
import es.eoi.java2022.recuerdamelon.service.mapper.UserServiceMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("El Id %d no existe", id)));
    }
    public User findByUsername(String username){return repository.findByUsername(username);}

    public User findByEmail(String email) {
        return repository.findByEmailIgnoreCase(email);
    }


    public List<Community> findCommunitiesByUserId(Integer userId){
        return repository.findById(userId)
                .orElseThrow(() -> new RuntimeException(String.format("El Id %d no existe", userId)))
                .getCommunities().stream().collect(Collectors.toList());
    }

    public List<Mensajes> findMensajesByUserId(Integer userId){
        return repository.findById(userId)
                .orElseThrow(() -> new RuntimeException(String.format("El Id %d no existe", userId)))
                .getMensajes().stream().collect(Collectors.toList());
    }

    public List<Task> findTasksByUserId(Integer userId){
        return repository.findById(userId)
                .orElseThrow(() -> new RuntimeException(String.format("El Id %d no existe", userId)))
                .getTasks().stream().collect(Collectors.toList());
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
    public User save(UserDTO userDTO) {
        if (userDTO.getId() == null){
            return repository.save(mapper.toEntity(userDTO));
        }
        throw new RuntimeException("Conflicto de tipo id");
    }

}