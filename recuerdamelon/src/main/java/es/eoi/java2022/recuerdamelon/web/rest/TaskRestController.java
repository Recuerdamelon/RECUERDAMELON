package es.eoi.java2022.recuerdamelon.web.rest;

import es.eoi.java2022.recuerdamelon.data.entity.Task;
import es.eoi.java2022.recuerdamelon.dto.TaskDTO;
import es.eoi.java2022.recuerdamelon.service.TaskService;
import es.eoi.java2022.recuerdamelon.service.mapper.TaskServiceMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api2") //api2?
public class TaskRestController {

    private final TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/task")
    public List<Task> findAll (@RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size){
        return taskService.findAll(PageRequest.of(page.orElse(1), size.orElse(10))); //Optional?¿?
    }

    @GetMapping("task/{id}")
    public Task findById (@PathVariable (value="id") Integer id){//OPTIONAL<>
        return taskService.findById(id); //Optional of nullable?¿?
    }

    @DeleteMapping("/task/{id}")
    public void deleteById (@PathVariable (value="id") Integer id){
        taskService.deleteById(id);
    }

    @PostMapping("/task")
    public TaskDTO save (TaskDTO taskDTO){
        return taskService.save(taskDTO);
    }

    @PutMapping("/task")
    public Task update (Task task){
        return taskService.update(task);
    }
}
