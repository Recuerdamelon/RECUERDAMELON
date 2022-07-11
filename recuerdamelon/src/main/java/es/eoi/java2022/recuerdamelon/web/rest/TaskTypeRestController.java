package es.eoi.java2022.recuerdamelon.web.rest;

import es.eoi.java2022.recuerdamelon.data.entity.TaskType;
import es.eoi.java2022.recuerdamelon.dto.TaskTypeDTO;
import es.eoi.java2022.recuerdamelon.service.TaskTypeService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasktype")
public class TaskTypeRestController {

    private final TaskTypeService taskTypeService;

    public TaskTypeRestController(TaskTypeService taskTypeService) {
        this.taskTypeService = taskTypeService;
    }


    @GetMapping("/tasktype")
    public List<TaskType> findAll(@RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size) {
        return taskTypeService.findAll(PageRequest.of(page.orElse(1), size.orElse(10)));
    }

    @GetMapping("/tasktype/{id}")
    public Optional<TaskType> findById(@PathVariable(value = "id") Integer id) { //Optional? DUDA 2
        return Optional.ofNullable(taskTypeService.findById(id));
    }

    @DeleteMapping("/tasktype/{id}")
    public void deleteById(@PathVariable(value = "id") Integer id) {
        taskTypeService.deleteById(id);
    }

    @PostMapping("/tasktype")
    public TaskTypeDTO save(TaskTypeDTO taskTypeDTO) {
        return taskTypeService.save(taskTypeDTO);
    }

    @PutMapping("/tasktype")
    public TaskType update(TaskType taskType) {
        return taskTypeService.update(taskType);
    }
}

