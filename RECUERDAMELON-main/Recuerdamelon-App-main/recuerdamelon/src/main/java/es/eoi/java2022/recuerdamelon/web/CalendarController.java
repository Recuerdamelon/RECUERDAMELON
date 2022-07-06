package es.eoi.java2022.recuerdamelon.web;

import es.eoi.java2022.recuerdamelon.data.entity.Calendar;
import es.eoi.java2022.recuerdamelon.service.CalendarService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class CalendarController {
    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }


    //Métodos...
    @GetMapping("/calendar")
    public String findAll (@RequestParam("page") Optional<Integer> page,
                                   @RequestParam("size") Optional<Integer> size, Model model){
        // Convierte parámetros page y size a pageable
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(10));
        model.addAttribute("list", calendarService.findAll(pageable));
        return "calendar/list";
    }

}
