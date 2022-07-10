package es.eoi.java2022.recuerdamelon.service;

import es.eoi.java2022.recuerdamelon.data.entity.Task;
import es.eoi.java2022.recuerdamelon.data.repository.TaskRepository;
import es.eoi.java2022.recuerdamelon.dto.TaskDTO;
import es.eoi.java2022.recuerdamelon.dto.TaskTypeDTO;
import es.eoi.java2022.recuerdamelon.service.mapper.TaskServiceMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskService {
    private final TaskRepository repository;
    private final TaskServiceMapper mapper;

    public TaskService(TaskRepository repository, TaskServiceMapper mapper, TaskTypeDTO type) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Task> findAll(Pageable pageable) {
        return repository.findAll(pageable).toList();

    }

    public Task findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("El Id %d no existe", id)));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    public Task update(Task task) {
        if (task.getId() == null){
            throw new RuntimeException("No se puede actualizar tareas que no tengan Identificador");
        }
        return repository.save(task);
    }
    public TaskDTO save(TaskDTO taskDTO) {
        if (taskDTO.getId() != null){
            throw new RuntimeException("El Identificador no puede ser nulo");
        }
        return mapper.toDto(repository.save(mapper.toEntity(taskDTO)));
    }

}