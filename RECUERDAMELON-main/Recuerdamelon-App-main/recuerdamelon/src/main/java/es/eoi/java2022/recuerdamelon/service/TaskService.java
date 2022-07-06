package es.eoi.java2022.recuerdamelon.service;

import es.eoi.java2022.recuerdamelon.data.entity.Task;
import es.eoi.java2022.recuerdamelon.data.repository.TaskRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> findAll(PageRequest of) {
        return repository.findAll();

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
    public Task save(Task task) {
        if (task.getId() != null){
            throw new RuntimeException("El Identificador no puede ser nulo");
        }
        return repository.save(task);
    }

}