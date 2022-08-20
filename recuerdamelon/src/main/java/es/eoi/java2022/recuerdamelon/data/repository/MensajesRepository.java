package es.eoi.java2022.recuerdamelon.data.repository;

import es.eoi.java2022.recuerdamelon.data.entity.Mensajes;
import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.dto.MensajesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajesRepository extends JpaRepository<Mensajes, Integer> {

    Page<Mensajes> findAll(Pageable pageable);
    Page<Mensajes> findByUsersId(Integer userId, Pageable pageable);

    Page<Mensajes> findByRecieverAndRecieved (Integer reciever, boolean recieved, Pageable pageable);
    Page<Mensajes> findByRecieverAndDeleted (Integer reciever, boolean deleted, Pageable pageable);
    Page<Mensajes> findByRecieverAndSaved (Integer reciever, boolean saved, Pageable pageable);
    Page<Mensajes> findByRecieverAndSent (Integer reciever, boolean sent, Pageable pageable);

}
