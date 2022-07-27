package es.eoi.java2022.recuerdamelon.data.repository;

import es.eoi.java2022.recuerdamelon.data.entity.Mensajes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajesRepository extends JpaRepository<Mensajes, Integer> {
    Page<Mensajes> findByUserId(Integer userId, Pageable pageable);
}
