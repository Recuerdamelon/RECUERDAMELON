package es.eoi.java2022.recuerdamelon.web.rest;

import es.eoi.java2022.recuerdamelon.data.entity.Notification;
import es.eoi.java2022.recuerdamelon.data.repository.NotificacionRepository;
import es.eoi.java2022.recuerdamelon.service.NotificationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notification")
public class NotificationRestController {

    private final NotificationService notificationService;


    public NotificationRestController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping ("/notification")
    public List<Notification> findAll (@RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size){
        return notificationService.findAll(PageRequest.of(page.orElse(1), size.orElse(10)));
    }

    @GetMapping ("/notification/{id}")
    public Optional<Notification> findById (@PathVariable (value = "id") Integer id){
        return Optional.ofNullable(notificationService.findById(id));
    }

    @DeleteMapping ("/notification/{id}")
    public void deleteById (@PathVariable (value = "id") Integer id){
        notificationService.deleteById(id);
    }

    @PostMapping ("/notification")
    public Notification save (Notification notification){
        return notificationService.save(notification);
    }

    @PutMapping ("/notification")
    public Notification update (Notification notification){
        return notificationService.update(notification);
    }
}