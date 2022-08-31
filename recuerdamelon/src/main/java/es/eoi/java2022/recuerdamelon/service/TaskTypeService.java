package es.eoi.java2022.recuerdamelon.service;

import es.eoi.java2022.recuerdamelon.data.entity.TaskType;
import es.eoi.java2022.recuerdamelon.data.repository.TaskTypeRepository;
import es.eoi.java2022.recuerdamelon.dto.TaskTypeDTO;
import es.eoi.java2022.recuerdamelon.service.mapper.TaskTypeServiceMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskTypeService {
    private final TaskTypeRepository repository;
    private final TaskTypeServiceMapper mapper;

    public TaskTypeService(TaskTypeRepository repository, TaskTypeServiceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<TaskType> findAll(Pageable pageable) {
        return repository.findAll(pageable).toList();

    }

    public TaskType findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("El Id %d no existe", id)));
    }

    public TaskTypeDTO findByName(String name) {
        return mapper.toDto( repository.findByName(name));
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
    public TaskTypeDTO save(TaskTypeDTO taskTypeDTO) {
        if (taskTypeDTO.getId() != null){
            throw new RuntimeException("El Identificador no puede ser nulo");
        }
        return mapper.toDto(repository.save(mapper.toEntity(taskTypeDTO)));
    }

}