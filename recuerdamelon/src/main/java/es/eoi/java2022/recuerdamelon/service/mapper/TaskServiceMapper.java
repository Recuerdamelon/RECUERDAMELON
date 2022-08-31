package es.eoi.java2022.recuerdamelon.service.mapper;


import es.eoi.java2022.recuerdamelon.data.entity.Task;
import es.eoi.java2022.recuerdamelon.dto.TaskDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {TaskTypeServiceMapper.class, UserServiceMapper.class})
public interface TaskServiceMapper extends IEntityMapper<Task, TaskDTO> {
}
