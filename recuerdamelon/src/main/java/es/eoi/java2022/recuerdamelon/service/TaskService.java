package es.eoi.java2022.recuerdamelon.service;

import es.eoi.java2022.recuerdamelon.data.entity.Community;
import es.eoi.java2022.recuerdamelon.data.entity.Task;
import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.data.repository.CommunityRepository;
import es.eoi.java2022.recuerdamelon.data.repository.TaskRepository;
import es.eoi.java2022.recuerdamelon.dto.TaskDTO;
import es.eoi.java2022.recuerdamelon.service.mapper.TaskServiceMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TaskService {
    private final TaskRepository repository;

    private final CommunityRepository communityRepository;

    private final TaskServiceMapper mapper;

    private final UserService service;

    public TaskService(TaskRepository repository, CommunityRepository communityRepository, TaskServiceMapper mapper, UserService service) {
        this.repository = repository;
        this.communityRepository = communityRepository;
        this.mapper = mapper;
        this.service = service;
    }

    public List<Task> findAll(Pageable pageable) {
        return repository.findAll(pageable).toList();

    }
    public List<TaskDTO> findByUser(User user) {
        Set<Integer> userIdsFromCommunities = communityRepository
                .findDistinctByUsersId(user.getId())
                .stream()
                .map(community -> community.getUsers().stream().map(User::getId).collect(Collectors.toSet()))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());


        return repository
                .findByUsersIdIn(userIdsFromCommunities)
                .stream()
                .map(mapper::toDto).collect(Collectors.toList());
    }


    public Task findById(Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException(String.format("El Id %d no existe", id)));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Task update(Task task) {
        if (task.getId() == null) {
            throw new RuntimeException("No se puede actualizar tareas que no tengan Identificador");
        }
        return repository.save(task);
    }

    public TaskDTO save(TaskDTO taskDTO) {
        if (taskDTO.getId() != null) {
            throw new RuntimeException("El Identificador no puede ser nulo");
        }
        return mapper.toDto(repository.save(mapper.toEntity(taskDTO)));
    }

}