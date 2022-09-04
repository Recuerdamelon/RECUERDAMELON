package es.eoi.java2022.recuerdamelon.service;

import es.eoi.java2022.recuerdamelon.data.entity.Task;
import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.data.repository.CommunityRepository;
import es.eoi.java2022.recuerdamelon.data.repository.TaskRepository;
import es.eoi.java2022.recuerdamelon.dto.TaskDTO;
import es.eoi.java2022.recuerdamelon.service.mapper.TaskServiceMapper;
import es.eoi.java2022.recuerdamelon.service.mapper.UserServiceMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repository;

    private final CommunityRepository communityRepository;

    private final TaskServiceMapper mapper;

    private final UserService service;

    public TaskService(TaskRepository repository, CommunityRepository communityRepository, UserService userService, UserServiceMapper mapperuser, TaskServiceMapper mapper, UserService service, CommunityService communityService) {
        this.repository = repository;
        this.communityRepository = communityRepository;
        this.mapper = mapper;
        this.service = service;
    }

    public List<Task> findAll(Pageable pageable) {
        return repository.findAll(pageable).toList();

    }

    public Task findById(Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException(String.format("El Id %d no existe", id)));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    /*
    public Task update(Task task) {
        if (task.getId() == null) {
            throw new RuntimeException("No se puede actualizar tareas que no tengan Identificador");
        }
        return repository.save(task);
    }
    */

    public TaskDTO save(TaskDTO taskDTO) {
        
        return mapper.toDto(repository.save(mapper.toEntity(taskDTO)));
    }

}
