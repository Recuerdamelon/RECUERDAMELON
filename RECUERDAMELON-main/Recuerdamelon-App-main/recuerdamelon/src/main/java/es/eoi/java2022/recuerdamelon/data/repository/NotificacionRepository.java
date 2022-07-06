package es.eoi.java2022.recuerdamelon.data.repository;


import es.eoi.java2022.recuerdamelon.data.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotificacionRepository extends JpaRepository<Notification,Integer> {
}
