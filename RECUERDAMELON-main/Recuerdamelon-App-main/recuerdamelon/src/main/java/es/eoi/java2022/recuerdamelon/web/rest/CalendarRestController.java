
package es.eoi.java2022.recuerdamelon.web.rest;

import es.eoi.java2022.recuerdamelon.data.entity.Calendar;
import es.eoi.java2022.recuerdamelon.service.CalendarService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController  //Contexto Spring
@RequestMapping("/api")
public class CalendarRestController {

    //Instancia
    private final CalendarService calendarService;

    public CalendarRestController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }


    //Métodos...
    @GetMapping("/calendar")
    public List<Calendar> findAll (@RequestParam("page") Optional<Integer> page,
                                   @RequestParam("size") Optional<Integer> size){
        // Convierte parámetros page y size a pageable
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(10));
        return calendarService.findAll(pageable);
    }

    @GetMapping("/calendar/{id}")
    public Calendar findById (@PathVariable (value="id") Integer id){
        return calendarService.findById(id);
    } //Optional¿?¿

    @DeleteMapping("/calendar/{id}")
    public void deleteById (@PathVariable (value = "id") Integer id){
        calendarService.deleteById(id);
    }

    @PostMapping("/calendar")
    public Calendar save (Calendar calendar){
        return calendarService.save(calendar);
    }

    @PutMapping("/calendar")
    public Calendar update (Calendar calendar){
        return calendarService.update(calendar);
    }
}

