package es.eoi.java2022.recuerdamelon.service.mapper;


import es.eoi.java2022.recuerdamelon.data.entity.Task;
import es.eoi.java2022.recuerdamelon.dto.TaskDTO;
import es.eoi.java2022.recuerdamelon.utils.DateUtil;
import org.springframework.stereotype.Service;



@Service
    public class TaskServiceMapper extends AbstractServiceMapper<Task, TaskDTO> {

        public Task toEntity(TaskDTO dto) {
            final Task entity = new Task();
            entity.setId(dto.getId());
            entity.setTitle(dto.getTitle());
            entity.setEndDate(DateUtil.stringToDate(dto.getEndDate()));
            entity.setStartDate(DateUtil.stringToDate(dto.getStartDate()));
            entity.setDescription(dto.getDescription());
            entity.setLocationUrl(dto.getLocationUrl());
            entity.setDelete(dto.getDelete());
            return entity;
        }

        public TaskDTO toDto(Task entity) {
            final TaskDTO dto = new TaskDTO();
            dto.setId(entity.getId());
            dto.setTitle(entity.getTitle());
            dto.setEndDate(DateUtil.dateToString(entity.getEndDate()));
            dto.setStartDate(DateUtil.dateToString(entity.getStartDate()));
            dto.setDescription(entity.getDescription());
            dto.setLocationUrl(entity.getLocationUrl());
            dto.setDelete(entity.getDelete());
            return dto;
        }
}
