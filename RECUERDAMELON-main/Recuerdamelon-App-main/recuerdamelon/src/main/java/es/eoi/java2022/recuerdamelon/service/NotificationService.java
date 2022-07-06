package es.eoi.java2022.recuerdamelon.service;

import es.eoi.java2022.recuerdamelon.data.entity.Notification;
import es.eoi.java2022.recuerdamelon.data.repository.NotificacionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotificationService {
    private final NotificacionRepository repository;

    public NotificationService(NotificacionRepository repository) {
        this.repository = repository;
    }

    public List<Notification> findAll(PageRequest of) {
        return repository.findAll();

    }

    public Notification findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("El Id %d no existe", id)));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    public Notification update(Notification notification) {
        if (notification.getId() == null){
            throw new RuntimeException("No se puede actualizar tareas que no tengan Identificador");
        }
        return repository.save(notification);
    }
    public Notification save(Notification notification) {
        if (notification.getId() != null){
            throw new RuntimeException("El Identificador no puede ser nulo");
        }
        return repository.save(notification);
    }

}