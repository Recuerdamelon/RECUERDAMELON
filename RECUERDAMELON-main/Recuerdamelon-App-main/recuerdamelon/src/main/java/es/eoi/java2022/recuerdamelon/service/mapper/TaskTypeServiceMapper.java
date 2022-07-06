package es.eoi.java2022.recuerdamelon.service.mapper;

import es.eoi.java2022.recuerdamelon.data.entity.TaskType;
import es.eoi.java2022.recuerdamelon.dto.TaskTypeDTO;

public class TaskTypeServiceMapper extends AbstractServiceMapper<TaskType, TaskTypeDTO> {
    public TaskType toEntity(TaskTypeDTO dto) {
        final TaskType entity = new TaskType();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        return entity;
    }

    public TaskTypeDTO toDto(TaskType entity) {
        final TaskTypeDTO dto = new TaskTypeDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        return dto;
    }
}

