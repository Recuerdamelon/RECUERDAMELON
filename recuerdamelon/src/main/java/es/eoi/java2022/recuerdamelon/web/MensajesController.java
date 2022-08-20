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
import es.eoi.java2022.recuerdamelon.service.mapper.MensajesServiceMapper;
import es.eoi.java2022.recuerdamelon.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    protected MensajesController(MensajesService servicio, MensajesRepository repository, MensajesServiceMapper mapper, PublicarMensaje publicarMensaje, UserRepository repo, UserService userService, CommunityService communityService) {
        this.service = servicio;
        this.repository = repository;
        this.mapper = mapper;
        this.publicarMensaje = publicarMensaje;
        this.repo = repo;
        this.userService = userService;
        this.communityService = communityService;
    }

    //RECIEVED//
    @GetMapping("/mensajes/list")
    public String getAllRecieved(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
                         Model model) {
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        if (user.getRoles().contains("ROLE_ADMIN")){
            final List<Mensajes> all = this.service.findAll( PageRequest.of(page.orElse(1) - 1, size.orElse(10)));
            model
                    .addAttribute("username", user.getUsername())
                    .addAttribute("mensajes", all)
                    .addAttribute("admin","");
        }
        else {
            final Page<MensajesDTO> all = this.service.findByRecieverAndRecieved(user.getId(), true,
                    PageRequest.of(page.orElse(1) - 1, size.orElse(10)));
            model
                    .addAttribute("username", user.getUsername())
                    .addAttribute("mensajes", all)
                    .addAttribute("admin","hidden");
        }
        return "mensajes/list";
    }

    //SENT//
    @GetMapping("/mensajes/sent")
    public String getAllSent(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
                         Model model) {
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        if (user.getRoles().contains("ROLE_ADMIN")){
            final List<Mensajes> all = this.service.findAll( PageRequest.of(page.orElse(1) - 1, size.orElse(10)));
            model
                    .addAttribute("username", user.getUsername())
                    .addAttribute("mensajes", all)
                    .addAttribute("admin","");
        }
        else {
            final Page<MensajesDTO> all = this.service.findByRecieverAndSent(user.getId(), true,
                    PageRequest.of(page.orElse(1) - 1, size.orElse(10)));
            model
                    .addAttribute("username", user.getUsername())
                    .addAttribute("mensajes", all)
                    .addAttribute("admin","hidden");
        }
        return "mensajes/sent";
    }

    //DELETE//
    @GetMapping("/mensajes/delete")
    public String getAllDelete(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
                             Model model) {
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        if (user.getRoles().contains("ROLE_ADMIN")){
            final List<Mensajes> all = this.service.findAll( PageRequest.of(page.orElse(1) - 1, size.orElse(10)));
            model
                    .addAttribute("username", user.getUsername())
                    .addAttribute("mensajes", all)
                    .addAttribute("admin","");
        }
        else {
            final Page<MensajesDTO> all = this.service.findByRecieverAndDeleted(user.getId(), true,
                    PageRequest.of(page.orElse(1) - 1, size.orElse(10)));
            model
                    .addAttribute("username", user.getUsername())
                    .addAttribute("mensajes", all)
                    .addAttribute("admin","hidden");
        }
        return "mensajes/delete";
    }

    //SENT//
    @GetMapping("/mensajes/saved")
    public String getAllSaved(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
                             Model model) {
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        if (user.getRoles().contains("ROLE_ADMIN")){
            final List<Mensajes> all = this.service.findAll( PageRequest.of(page.orElse(1) - 1, size.orElse(10)));
            model
                    .addAttribute("username", user.getUsername())
                    .addAttribute("mensajes", all)
                    .addAttribute("admin","");
        }
        else {
            final Page<MensajesDTO> all = this.service.findByRecieverAndSaved(user.getId(), true,
                    PageRequest.of(page.orElse(1) - 1, size.orElse(10)));
            model
                    .addAttribute("username", user.getUsername())
                    .addAttribute("mensajes", all)
                    .addAttribute("admin","hidden");
        }
        return "mensajes/saved";
    }

    @GetMapping("/mensajes/create")
    public String create(ModelMap model) {
        final MensajesDTO dto = new MensajesDTO();
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<String> mailed = new ArrayList<>();
        for (Community communities: userService.findCommunitiesByUserId(user.getId())) {
            for (User getMail: communityService.findFriends(communities.getId())) {
                mailed.add(getMail.getUsername());
            }
        }
//        dto.setRecievers(mailed);
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
//            dto.setMensaje(dto.getMensaje());
//            dto.setUserId(user.getId() );
//            dto.setSent(true);
//            dto.setDelete(false);
//            dto.setRecieved(false);
//            dto.setSender(user.getUsername());
//            dto.setDate(DateUtil.dateToString(zonedDateTime));
            this.repository.save(service.save(dto));
            //Generar evento
        publicarMensaje.EnviarMensajeSaludo1(dto) ;
        return "redirect:/mensajes/list";
    }

//    @Transactional     //IF THE LIST IS EMPTY?
//    @PostMapping({"/mensajes/edit", "/mensajes/create"})
//    public String save(MensajesDTO dto, List<User> recievers) {
//        RecieversCreationDto creationDto=new RecieversCreationDto();
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        dto.setUserId(user.getId() );
//        for (User mailed:recievers) {
//            dto.setReciever(mailed.getId());
//            dto.setDate(DateUtil.timeStamp(timestamp));
//            dto.setSent(true);
//            System.out.println("6" + dto.getReciever());
//            service.save(dto);
//        }
//        //Generar evento
//        publicarMensaje.EnviarMensajeSaludo1(dto) ;
//        return "redirect:/mensajes/list";
//    }


//
//    @Transactional
//    @PostMapping(value = { "/mensajes/edit/todraw" })
//    public String toDraw (MensajesDTO dto, ModelMap model) {
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        dto.setUserId(user.getId());
//        dto.setDate(DateUtil.timeStamp(timestamp));
//        dto.setSaved(true);
//        dto.setSent(false);
//        dto.setMensaje(dto.getMensaje());
//        dto.setReciever(0);
//
//        return "redirect:/mensajes/saved";
//    }

}
