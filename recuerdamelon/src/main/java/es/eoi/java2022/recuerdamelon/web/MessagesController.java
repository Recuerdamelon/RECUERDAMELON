package es.eoi.java2022.recuerdamelon.web;


import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.dto.MessagesDTO;
import es.eoi.java2022.recuerdamelon.listeners.MessageListenerGlobalAsinc;
import es.eoi.java2022.recuerdamelon.service.MessagesService;
import es.eoi.java2022.recuerdamelon.service.PublicarMensaje;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Controller
public class MessagesController {
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    private final MessagesService service;
    private final PublicarMensaje publicarMensaje;

    private static final Logger logger = LoggerFactory.getLogger(MessageListenerGlobalAsinc.class);

    @Autowired
    protected MessagesController(MessagesService servicio, PublicarMensaje publicarMensaje) {
        this.service = servicio;
        this.publicarMensaje = publicarMensaje;
    }
    @GetMapping("/messages")
    public String getAll(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
                         Model model) {
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        final Page<MessagesDTO> all = this.service.findByUserId(user.getId(), PageRequest.of(page.orElse(1) - 1,
                            size.orElse(10)));
        model
                .addAttribute("username", user.getUsername())
                .addAttribute("messages", all);
        return "messages/list";
    }

    @GetMapping("/messages/create")
    public String create(ModelMap model) {
        final MessagesDTO dto = new MessagesDTO();
        model.addAttribute("message", dto);
        return "messages/edit";
    }

    @Transactional
    @PostMapping(value = { "/messages/create" })
    public String save(MessagesDTO dto) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        dto.setUserId(user.getId() );
        String message = dto.getMessage();
        message += ":";
        message += sdf1.format(timestamp);
        dto.setMessage(message);
        logger.info("Antes de lanzar el evento: " + dto.toString());
        //Generar evento
        publicarMensaje.EnviarMensajeSaludo(dto) ;
        return "redirect:/messages";
    }
}
