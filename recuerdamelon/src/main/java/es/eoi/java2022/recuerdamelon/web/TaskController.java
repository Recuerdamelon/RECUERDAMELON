package es.eoi.java2022.recuerdamelon.web;

import es.eoi.java2022.recuerdamelon.data.entity.Community;
import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.dto.CommunityDTO;
import es.eoi.java2022.recuerdamelon.dto.HorarioDTO;
import es.eoi.java2022.recuerdamelon.dto.TaskDTO;
import es.eoi.java2022.recuerdamelon.dto.UserDTO;
import es.eoi.java2022.recuerdamelon.service.CommunityService;
import es.eoi.java2022.recuerdamelon.service.TaskService;
import es.eoi.java2022.recuerdamelon.service.TaskTypeService;
import es.eoi.java2022.recuerdamelon.service.UserService;
import es.eoi.java2022.recuerdamelon.service.mapper.CommunityServiceMapper;
import es.eoi.java2022.recuerdamelon.service.mapper.UserServiceMapper;
import es.eoi.java2022.recuerdamelon.utils.DateUtil;
import es.eoi.java2022.recuerdamelon.utils.TaskHorario;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@Controller
public class TaskController {

    private final TaskService taskService;

    private  final TaskTypeService taskTypeService;
    private final CommunityService communityService;
private final UserServiceMapper serviceMapper;
    private  final UserService userService;

    public TaskController(TaskService taskService, TaskTypeService taskTypeService, CommunityService communityService, CommunityServiceMapper communityServiceMapper, UserServiceMapper serviceMapper, UserService userService) {
        this.taskService = taskService;
        this.taskTypeService = taskTypeService;
        this.communityService = communityService;
        this.serviceMapper = serviceMapper;
        this.userService = userService;
    }

//********************************************    CRUD     *******************************************//
    //             ---------------------------GET Methods-----------------------------         //
    //# READ...
//    @GetMapping("/tasks/admin")
////    @PostAuthorize("hasRole('ROLE_ADMIN') or #model[tasks].ownerId == authentication.principal.id")
//    public String findAll(@RequestParam("page") Optional<Integer> page,
//                          @RequestParam("size") Optional<Integer> size, Model model) {
//        // Convierte parámetros page y size a pageable
//        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(10));
//        model.addAttribute("tasks", taskService.findAll(pageable));
//        return "tasks";
//    }

    @GetMapping("/tasks")
//    @PostAuthorize("#model[task].userId == authentication.principal.id")
    public String findByUser( ModelMap model) {
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("tasks", this.userService.findTasksByUserId(user.getId()));
        return "tasks";
    }

    /*--- VER DETALLES DE LA TASK ¿?¿?*/
    @GetMapping("/task/{id}")
    @PostAuthorize("hasRole('ROLE_ADMIN') or #model[task].userId == authentication.principal.id")
    public String detail(@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("task", this.taskService.findById(id) );
        return "task/detail";
    }


    /* ====== DELETE ======= */
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
    
        //Horarios en tasck Business
    @GetMapping("/business/horario")
        public  String getHorario(WebRequest request, Model model){
        HorarioDTO horarioDTO = new HorarioDTO();
        TaskDTO taskDTO = new TaskDTO();
        CommunityDTO communityDTO = new CommunityDTO();
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        List<Community> equipos = userService.findCommunitiesByUserId(user.getId());
    //    horarioDTO.setEquipo(equipos);
        model.addAttribute("task",taskDTO);
        model.addAttribute("horarios",horarioDTO);
        model.addAttribute("equipos", equipos);
        return "TBS";
    }
    @Transactional
    @PostMapping("/business/horario")
    public String saveHorario(TaskDTO taskDTO, HorarioDTO horarioDTO){
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        List<Community> equipos = horarioDTO.getEquipos();
        Set<UserDTO> members = new HashSet<>();
        for (Community team:equipos) {
            for (User member:communityService.findFriends(team.getId())){
                members.add(serviceMapper.toDto(member));
            }
        }
        taskDTO.setUsers(members);

        List<String > start = horarioDTO.getStartLocalDateTime();

        List<String > end = horarioDTO.getEndLocalDateTime();


        Map<String,String> map = new HashMap<>();

        map = TaskHorario.map(start,end);
        for (String descripcion:horarioDTO.getTask()) {
            for (var entry : map.entrySet()){
                    taskDTO.setHorario(true);
                    taskDTO.setDescription(descripcion);
                    taskDTO.setStartDate( DateUtil.dateToString1(DateUtil.stringToDate1(entry.getKey())));
                    taskDTO.setEndDate(DateUtil.dateToString1(DateUtil.stringToDate1( entry.getValue())));
                    taskDTO.setDeleted(false);
                    taskDTO.setTaskType(taskTypeService.findByName("business"));
                    this.taskService.save(taskDTO);
                }
            }

     return "redirect:/tasks";
    }

    //Horarios en tasck usuario
    @GetMapping("/user/horario")
    public  String gettask(WebRequest request, Model model){
        HorarioDTO horarioDTO = new HorarioDTO();
        TaskDTO taskDTO = new TaskDTO();
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("task",taskDTO);
        model.addAttribute("horarios",horarioDTO);
        return "THU";
    }
    @Transactional
    @PostMapping("/user/horario")
    public String saveTask(TaskDTO taskDTO, HorarioDTO horarioDTO){
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        Set<UserDTO> members = new HashSet<>();
        members.add(serviceMapper.toDto(userService.findByUsername(user.getUsername())));
        taskDTO.setUsers(members);

        List<String > start = horarioDTO.getStartLocalDateTime();

        List<String > end = horarioDTO.getEndLocalDateTime();


        Map<String,String> map = new HashMap<>();

        map = TaskHorario.map(start,end);
        for (String descripcion:horarioDTO.getTask()) {
            for (var entry : map.entrySet()){
                taskDTO.setHorario(true);
                taskDTO.setDescription(descripcion);
                taskDTO.setStartDate( DateUtil.dateToString1(DateUtil.stringToDate1(entry.getKey())));
                taskDTO.setEndDate(DateUtil.dateToString1(DateUtil.stringToDate1( entry.getValue())));
                taskDTO.setDeleted(false);
                taskDTO.setTaskType(taskTypeService.findByName("user"));
                this.taskService.save(taskDTO);
            }
        }

        return "redirect:/tasks";
    }
    
    /* =========== TASK EDIT ============ */

    @GetMapping("/task/{id}/edit")//get de update -create&update-//
    /*@PostAuthorize("hasRole('ROLE_ADMIN') or #model[task].userId == authentication.principal.id")*/
    public String update (@PathVariable("id") Integer id, ModelMap model) {

        HorarioDTO horarioDTO = new HorarioDTO();
        TaskDTO taskDTO = new TaskDTO();
        CommunityDTO communityDTO = new CommunityDTO();
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        /* SELECT: Incluye comunities a las que pertenezco */
        List<Community> equipos = userService.findCommunitiesByUserId(user.getId());


        /* SELECT: Incluye usr de mis friends */
        List<User> friends = new ArrayList<>();
        for (Community team:equipos) {
            for (User member:communityService.findFriends(team.getId())){
                if (!friends.contains(member) ) {
                    friends.add(member);
                }
            }
        }
        /*hacer foreach en caso de edit tarea BUSINESS*/
        /*
            if(this.taskService.findById(id).getHorario())
            }

            }
        */

        model.addAttribute("task", this.taskService.findById(id));
        model.addAttribute("horarios",horarioDTO);
        model.addAttribute("equipos", equipos);
        model.addAttribute("friends", friends);

        if(!this.taskService.findById(id).getHorario())
            return "TUS";
        else{
            return "TBS";
        }
    }

    /*======= CREATE USER TASK =======*/

    @GetMapping("/task/create")
    public  String getUserTask(WebRequest request, Model model){
        HorarioDTO horarioDTO = new HorarioDTO();
        TaskDTO taskDTO = new TaskDTO();
        CommunityDTO communityDTO = new CommunityDTO();
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        /* SELECT: Incluye comunities a las que pertenezco */
        List<Community> equipos = userService.findCommunitiesByUserId(user.getId());
        /*equipos.remove(userService.findCommunitiesByUserId(user.getId()));*/ /*TODO: necesario si seleciona varias comunity*/


        /* SELECT: Incluye usr de mis friends */
        List<User> friends = new ArrayList<>();
        for (Community team:equipos) {
            for (User member:communityService.findFriends(team.getId())){
                if (!friends.contains(member) ) {
                    friends.add(member);
                }
            }
        }

        model.addAttribute("task",taskDTO);
        model.addAttribute("horarios",horarioDTO);
        model.addAttribute("equipos", equipos);
        model.addAttribute("friends", friends);
        return "TUS";
    }

    @Transactional
    /*@PostMapping("/task/create")*/
    @PostMapping(value = {"/task/{id}/edit", "/task/create"})
    public String saveUserTask(TaskDTO taskDTO, HorarioDTO horarioDTO){
    /*public String saveUserTask(TaskDTO taskDTO){*/
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        /* INICIALIZAMOS si es nueva la tarea */
        taskDTO.setHorario(false);
        taskDTO.setDeleted(false);
        taskDTO.setTaskType(taskTypeService.findByName("user"));


        /* ------------ COMPARTIR USER TASK ------------- */
        Set<UserDTO> members = new HashSet<>();
        List<Integer>idShare = new ArrayList<>();

            /* ADD: usr de comunities a las que pertenezco */
            List<Community> equipos = horarioDTO.getEquipos();
            for (Community team : equipos) {
                for (User member : communityService.findFriends(team.getId())) {
                    if (!idShare.contains(member.getId())) {
                        members.add(serviceMapper.toDto(member));
                        idShare.add(member.getId());
                    }
                }
            }

            /* ADD: usr de mis friends */
            List<User> friends = horarioDTO.getFriends();
            for (User member:friends){
                if (!idShare.contains(member.getId())){
                    members.add(serviceMapper.toDto(member));
                }
            }

            /* OWN: para tareas que no se comparten */
            if(equipos.isEmpty()){
            members.add(serviceMapper.toDto(user));
            }

            taskDTO.setUsers(members);

        this.taskService.save(taskDTO);
        return "redirect:/tasks";
    }
    
}
