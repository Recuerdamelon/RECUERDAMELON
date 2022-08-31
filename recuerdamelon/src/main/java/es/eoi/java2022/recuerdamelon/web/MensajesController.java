package es.eoi.java2022.recuerdamelon.web;


import es.eoi.java2022.recuerdamelon.data.entity.Community;
import es.eoi.java2022.recuerdamelon.data.entity.Mensajes;
import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.data.repository.MensajesRepository;
import es.eoi.java2022.recuerdamelon.data.repository.UserRepository;
import es.eoi.java2022.recuerdamelon.dto.MensajesDTO;
import es.eoi.java2022.recuerdamelon.service.CommunityService;
import es.eoi.java2022.recuerdamelon.service.MensajesService;
import es.eoi.java2022.recuerdamelon.service.PublicarMensaje;
import es.eoi.java2022.recuerdamelon.service.UserService;
import es.eoi.java2022.recuerdamelon.service.mapper.CommunityServiceMapper;
import es.eoi.java2022.recuerdamelon.service.mapper.MensajesServiceMapper;
import es.eoi.java2022.recuerdamelon.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Controller
public class MensajesController {

    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    private final MensajesService service;
    private final MensajesRepository repository;
    private final MensajesServiceMapper mapper;
    private final PublicarMensaje publicarMensaje;
    public final UserRepository repo;
    private final UserService userService;
    private final CommunityService communityService;
    private final CommunityServiceMapper communityServiceMapper;

    @Autowired
    protected MensajesController(MensajesService servicio, MensajesRepository repository, MensajesServiceMapper mapper, PublicarMensaje publicarMensaje, UserRepository repo, UserService userService, CommunityService communityService, CommunityServiceMapper communityServiceMapper) {
        this.service = servicio;
        this.repository = repository;
        this.mapper = mapper;
        this.publicarMensaje = publicarMensaje;
        this.repo = repo;
        this.userService = userService;
        this.communityService = communityService;
        this.communityServiceMapper = communityServiceMapper;
    }

    //RECIEVED//
    @GetMapping("/mensajes/list")
    public String getAllRecieved(Model model) {
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        final List<Mensajes> all = this.service.findByRecieved(user.getId(), true);
        model.addAttribute("mensajes", all);
        return "mensajes/list";
    }

    //SENT//
    @GetMapping("/mensajes/sent")
    public String getAllSent(Model model) {
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        final List<Mensajes> all = this.service.findBySent(user.getId(), true);
        model.addAttribute("mensajes", all);
        return "mensajes/sent";
    }

    //DELETE//
    @GetMapping("/mensajes/delete")
    public String getAllDelete(Model model) {
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        final List<Mensajes> all = this.service.findByDeleted(user.getId(), true);
        model.addAttribute("mensajes", all);
        return "mensajes/delete";
    }

    //SENT//
    @GetMapping("/mensajes/saved")
    public String getAllSaved(Model model) {
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        final List<Mensajes> all = this.service.findBySaved(user.getId(), true);
        model.addAttribute("mensajes", all);
        return "mensajes/saved";
    }

    //INVITATION//
    @GetMapping("/mensajes/invited")
    public String getAllInvitations(Model model) {
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        final List<Mensajes> all = this.service.findByInvitation(user.getId(), true);
        model.addAttribute("mensajes", all);
        return "mensajes/invited";
    }

    @GetMapping("/mensajes/create")
    public String create(ModelMap model) {
        final MensajesDTO dto = new MensajesDTO();
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<User> mailed = new ArrayList<>();
        for (Community communities : userService.findCommunitiesByUserId(user.getId())) {
            mailed.addAll(communityService.findFriends(communities.getId()));
            mailed.remove(userService.findById(user.getId()));
        }

        //dto.setUsers(mailed); //MANDAR LISTA CON COMMUNITIES??
        model.addAttribute("users", mailed);
        model.addAttribute("mensaje", dto);
        return "mensajes/edit";
    }

    @Transactional     //IF THE LIST IS EMPTY?
    @PostMapping({"/mensajes/edit", "/mensajes/create"})
    public String save(MensajesDTO dto) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

            dto.setUserId(user.getId() );
            dto.setSender(user.getUsername());
            dto.setDate(DateUtil.dateToString(zonedDateTime));
            dto.setRecieved(true);

            this.repository.save(service.save(dto));


        //Generar evento
        publicarMensaje.EnviarMensajeSaludo1(dto);
        return "redirect:/mensajes/list";
    }

    //@Transactional GUARDAR EN BORRADORES
    @GetMapping({"/mensajes/save"})
    public String toDraw(MensajesDTO dto) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            dto.setMensaje(dto.getMensaje());
            dto.setTitle(dto.getTitle());
            dto.setSender(user.getUsername());
            dto.setDate(DateUtil.dateToString(zonedDateTime));
            dto.setSaved(true);
        this.repository.save(service.save(dto));
        //Generar evento
        publicarMensaje.EnviarMensajeSaludo1(dto);
        return "redirect:/mensajes/list";
    }

    @GetMapping("/mensajes/{id}/detail")
    public String create(@PathVariable("id") Integer id, ModelMap model) {
        Mensajes mensaje = service.findById(id);
        model.addAttribute("mensaje", mensaje);
        return "mensajes/detail";
    }

    @GetMapping("/mensajes/{id}/delete")
    public String deleted(@PathVariable("id") Integer id, ModelMap model) {
        Mensajes mensaje = service.findById(id);
        mensaje.setDeleted(true);
        mensaje.setRecieved(false);
        mensaje.setSaved(false);
        mensaje.setSent(false);
        service.save(mapper.toDto(mensaje));
        return "redirect:/mensajes/list";
    }

    @GetMapping("/mensajes/{id}/FinalDelete")
    public String delete(@PathVariable("id") Integer id, ModelMap model) {
        Mensajes mensaje = service.findById(id);
        this.service.delete(mensaje);
        return "redirect:/mensajes/delete";
    }

    @GetMapping("/mensajes/DeleteAll")
    public String deleteAll(ModelMap model) {
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<Mensajes> toDelete = service.findByDeleted(user.getId(), true);
        for (Mensajes m : toDelete) {
            this.service.delete(m);
        }
        return "redirect:/mensajes/delete";
    }

    @GetMapping("/mensajes/{id}/{community}/acept")
    public String acept(@PathVariable("community") String name, @PathVariable("id") Integer id, ModelMap model) {
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());


        Community community = communityService.findByName(name);
        Set<User> toAdd = new HashSet<>();
        for (User userin:community.getUsers()) {
            toAdd.add(userin);
        }
        toAdd.add(user);
        community.setUsers(toAdd);
        communityService.save(communityServiceMapper.toDto(community));
        Mensajes mensajes = service.findById(id);
        service.delete(mensajes);


        Mensajes mensajes1 = new Mensajes();
        mensajes1.setMensaje("El usuario " + user.getUsername() + " se ha unido a la comunidad " + name);
        mensajes1.setInvitation(true);
        mensajes1.setCommunity(name);
        mensajes1.setAcepted(true);
        mensajes1.setDate(DateUtil.dateToString(zonedDateTime));
        Set<User> friends = new HashSet<>();
        for (User friend : communityService.findFriends(community.getId())) {
            friends.add(friend);
        }
        mensajes1.setUsers(friends);
        mensajes1.setSender(user.getUsername());
        service.save(mapper.toDto(mensajes1));


        return "redirect:/mensajes/invited";
    }

    @GetMapping("/mensajes/{id}/{community}/decline")
    public String decline(@PathVariable("community") String name, @PathVariable("id") Integer id, ModelMap model) {
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Community community = communityService.findByName(name);
        MensajesDTO dto = new MensajesDTO();
        dto.setMensaje("El usuario " + user.getUsername() + " ha rechazado la invitación a la comunidad " + name);
        dto.setSender(user.getUsername());
        dto.setCommunity(name);
        dto.setRecieved(true);
        List<User> friends = new ArrayList<>();
        for (User friend : communityService.findFriends(community.getId())) {

            if (friend.getId() == community.getAdmin())
                friends.add(friend);
        }
        dto.setUsers(friends);
        service.save(dto);
        service.delete(service.findById(id));
        return "redirect:/mensajes/invited";
    }
}
