package es.eoi.java2022.recuerdamelon.service;

import es.eoi.java2022.recuerdamelon.data.entity.Notification;
import es.eoi.java2022.recuerdamelon.data.repository.NotificacionRepository;
import es.eoi.java2022.recuerdamelon.dto.NotificationDTO;
import es.eoi.java2022.recuerdamelon.service.mapper.NotificationServiceMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotificationService {
    private final NotificacionRepository repository;
    private final NotificationServiceMapper mapper;

    public NotificationService(NotificacionRepository repository, NotificationServiceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Notification> findAll(Pageable pageable) {
        return repository.findAll(pageable).toList();

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
    public Notification save(NotificationDTO notificationDTO) {
        if (notificationDTO.getId() != null){
            throw new RuntimeException("El Identificador no puede ser nulo");
        }
        return repository.save(mapper.toEntity(notificationDTO));
    }

}