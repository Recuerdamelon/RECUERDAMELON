package es.eoi.java2022.recuerdamelon.service;

import es.eoi.java2022.recuerdamelon.data.entity.TaskType;
import es.eoi.java2022.recuerdamelon.data.repository.TaskTypeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskTypeService {
    private final TaskTypeRepository repository;

    public TaskTypeService(TaskTypeRepository repository) {
        this.repository = repository;
    }

    public List<TaskType> findAll(PageRequest of) {
        return repository.findAll();

    }

    public TaskType findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("El Id %d no existe", id)));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    public TaskType update(TaskType taskType) {
        if (taskType.getId() == null){
            throw new RuntimeException("No se puede actualizar tareas que no tengan Identificador");
        }
        return repository.save(taskType);
    }
    public TaskType save(TaskType taskType) {
        if (taskType.getId() != null){
            throw new RuntimeException("El Identificador no puede ser nulo");
        }
        return repository.save(taskType);
    }

}