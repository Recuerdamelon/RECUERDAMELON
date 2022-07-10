package es.eoi.java2022.recuerdamelon.web;

import es.eoi.java2022.recuerdamelon.dto.NotificationDTO;
import es.eoi.java2022.recuerdamelon.dto.UserRoleDTO;
import es.eoi.java2022.recuerdamelon.service.NotificationService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


//********************************************    CRUD     *******************************************//
    //             ---------------------------GET Methods-----------------------------         //
    //# READ...
    @GetMapping("/notification")
    @PostAuthorize("hasRole('ROLE_ADMIN')")
    public String findAll(@RequestParam("page") Optional<Integer> page,
                          @RequestParam("size") Optional<Integer> size, Model model) {
        // Convierte parámetros page y size a pageable
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(10));
        model.addAttribute("list", notificationService.findAll(pageable));
        return "notification/list";
    }

    @GetMapping("/notification/{id}")
    @PostAuthorize("hasRole('ROLE_ADMIN') or #model[notification].userId == authentication.principal.id") //Just Admin, users could know it via User info
    public String findById(@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("notification", this.notificationService.findById(id));
        return "notification/{id}/list";
    }

    //# UPDATE % CREATE...
    @GetMapping("/notification/{id}/edit")//get de update -create&update-//
    @PostAuthorize("hasRole('ROLE_ADMIN') or #model[notification].userId == authentication.principal.id") //Just Admin
    public String update (@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("notification", this.notificationService.findById(id));
        return "notification/edit";
    }


    //             ---------------------------POST Methods-----------------------------         //

    @Transactional //post transaccional del get de update//
    @PostMapping(value = {"/notification/{id}/edit"})
    @PreAuthorize("hasRole('ROLE_ADMIN') or #model[notification].userId == authentication.principal.id")//PRE // Just Admin
    public String save(NotificationDTO dto) {
        return String.format("redirect:/notification/%s",
                this.notificationService.save(dto).getId());
    }

    //# DELETE
    @PostMapping("/notification/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #model[notification].userId == authentication.principal.id") //PRE //Just Admin
    public Object deleteById(@PathVariable("id") Integer id, SessionStatus status) {
        try {
            this.notificationService.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            status.setComplete();//Limpieza de atributos de session para no provocar salida de session

            //Método de Spring que devuelve Model y View de una sola vez al controller
            return new ModelAndView("error/errorHapus")//url del view...

                    //Añadimos los atributos de la sesión
                    .addObject("entityId", id)
                    .addObject("entityName", "userrole")
                    //Añadimos un registro de la excepción como atributo
                    .addObject("errorCause", e.getRootCause().getMessage())
                    //Y añadimos atributo link para volver a notification
                    .addObject("backLink", "/notification");
        }
        status.setComplete();//Restablecemos atributos de session tras eliminar y...
        return "redirect:/notification";//...redirigimos a "/notification"
    }


}
