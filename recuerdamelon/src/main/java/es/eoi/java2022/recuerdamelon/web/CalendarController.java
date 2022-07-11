package es.eoi.java2022.recuerdamelon.web;

import es.eoi.java2022.recuerdamelon.data.entity.Calendar;
import es.eoi.java2022.recuerdamelon.dto.CalendarDTO;
import es.eoi.java2022.recuerdamelon.service.CalendarService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class CalendarController {
    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

//********************************************    CRUD     *******************************************//
    //             ---------------------------GET Methods-----------------------------         //
            //# READ...
    @GetMapping("/calendar")
    @PostAuthorize("hasRole('ROLE_ADMIN')")
    public String findAll(@RequestParam("page") Optional<Integer> page,
                          @RequestParam("size") Optional<Integer> size, Model model) {
        // Convierte parámetros page y size a pageable
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(10));
        model.addAttribute("list", calendarService.findAll(pageable));
        return "calendar/list";
    }

    @GetMapping("/calendar/{id}")
    @PostAuthorize("hasRole('ROLE_ADMIN') or #model[calendar].userId == authentication.principal.id")
    public String findById(@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("calendar", this.calendarService.findById(id));
        return "calendar/{id}/list";
    }

        //# UPDATE % CREATE...
    @GetMapping("/calendar/{id}/edit")//get de update -create&update-//
    @PostAuthorize("hasRole('ROLE_ADMIN') or #model[calendar].userId == authentication.principal.id")
    public String update (@PathVariable("id") Integer id, ModelMap model) {
        model.addAttribute("calendar", this.calendarService.findById(id));
        return "calendar/edit";
    }


    //             ---------------------------POST Methods-----------------------------         //

    @Transactional //post transaccional del get de update//
    @PostMapping(value = {"/calendar/{id}/edit"})
    @PreAuthorize("hasRole('ROLE_ADMIN') or #model[calendar].userId == authentication.principal.id")//PRE
    public String save(CalendarDTO dto) {
        return String.format("redirect:/calendars/%s",
                this.calendarService.save(dto).getId());
    }

        //# DELETE
    @PostMapping("/calendar/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #model[calendar].userId == authentication.principal.id") //PRE
    public Object deleteById(@PathVariable("id") Integer id, SessionStatus status) {
        try {
            this.calendarService.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            status.setComplete();//Limpieza de atributos de session para no provocar salida de session

            //Método de Spring que devuelve Model y View de una sola vez al controller
            return new ModelAndView("error/errorHapus")//url del view...

                    //Añadimos los atributos de la sesión
                    .addObject("entityId", id)
                    .addObject("entityName", "calendar")
                    //Añadimos un registro de la excepción como atributo
                    .addObject("errorCause", e.getRootCause().getMessage())
                    //Y añadimos atributo link para volver a calendar
                    .addObject("backLink", "/calendar");
        }
        status.setComplete();//Restablecemos atributos de session tras eliminar y...
        return "redirect:/calendar";//...redirigimos a "/user"
    }



}
