package es.eoi.java2022.recuerdamelon.web;

import es.eoi.java2022.recuerdamelon.dto.TaskDTO;
import es.eoi.java2022.recuerdamelon.dto.service.TaskService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

//********************************************    CRUD     *******************************************//
    //             ---------------------------GET Methods-----------------------------         //
    //# READ...
    @GetMapping("/tasks")
//    @PostAuthorize("hasRole('ROLE_ADMIN') or #model[tasks].ownerId == authentication.principal.id")
    public String findAll(@RequestParam("page") Optional<Integer> page,
                          @RequestParam("size") Optional<Integer> size, Model model) {
        // Convierte parámetros page y size a pageable
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(10));
        model.addAttribute("tasks", taskService.findAll(pageable));
        return "tasks";
    }

    @GetMapping("/task/{id}")
    @PostAuthorize("#model[task].userId == authentication.principal.id")
    public String findById(@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("task", this.taskService.findById(id));
        return "task/{id}/list";
    }

    //# UPDATE % CREATE...
    @GetMapping("/task/{id}/edit")//get de update -create&update-//
    @PostAuthorize("hasRole('ROLE_ADMIN') or #model[task].userId == authentication.principal.id")
    public String update (@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("task", this.taskService.findById(id));
        return "task/edit";
    }



      //#CREATE USER TASK
      @GetMapping("/task/create")
      public String getTask(WebRequest request, Model model) {
          TaskDTO taskDTO = new TaskDTO();
          model.addAttribute("task", taskDTO);
          return "task";
      }

   /* @Transactional
    @PostMapping("/task/create")*/
//    public String saveTask(TaskDTO taskDTO, Model model, Errors errors, RedirectAttributes redirectAttributes) {
//        List<Task> allTask = taskService.findAll(Pageable.unpaged());
//        if (allTask.contains(taskService.findByTitle(taskDTO.getTitle()))) {
//            redirectAttributes.addFlashAttribute("errortitle", true);
//            return "redirect:/task"; /*return "redirect:/task/create";*/
//        }
//        else {

//            this.taskRepository.save(taskService.save(taskDTO));
//            return "task"; /*return "redirect:/task/create";*/
//        }
//    }



    //             ---------------------------POST Methods-----------------------------         //

    @Transactional //post transaccional del get de update//
    @PostMapping(value = {"/task/{id}/edit"})
    @PostAuthorize("hasRole('ROLE_ADMIN') or #model[task].userId == authentication.principal.id")
    public String save(TaskDTO dto) {
        return String.format("redirect:/tasks/%s",
                this.taskService.save(dto).getId());
    }

    //# DELETE
    @PostMapping("/task/delete")
    // @PreAuthorize("hasRole('ROLE_ADMIN') or #model[task].userId == authentication.principal.id") //es una `LISTA` de tareas, como asegurar que toda la lista es nuestra?
    public Object deleteById(@RequestParam Integer[] taskid, SessionStatus status, ModelMap model) {
        try {
            for (int i = 0; i < taskid.length; i++) {
                this.taskService.deleteById(taskid[i]);
            }
        } catch (DataIntegrityViolationException e) {
            System.out.println("ERROR AL BORRAR TAREAS" + e.getMessage());
        }
        status.setComplete();//Restablecemos atributos de session tras eliminar y...
        return "redirect:/tasks";//...redirigimos a "/tasks"
    }



}
