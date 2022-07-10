package es.eoi.java2022.recuerdamelon.service.mapper;


import es.eoi.java2022.recuerdamelon.data.entity.Task;
import es.eoi.java2022.recuerdamelon.data.entity.TaskType;
import es.eoi.java2022.recuerdamelon.dto.TaskDTO;
import es.eoi.java2022.recuerdamelon.dto.TaskTypeDTO;
import es.eoi.java2022.recuerdamelon.utils.DateUtil;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;


@Mapper(componentModel = "spring", uses = {TaskTypeServiceMapper.class})
    public interface TaskServiceMapper extends IEntityMapper<Task, TaskDTO> {
}
