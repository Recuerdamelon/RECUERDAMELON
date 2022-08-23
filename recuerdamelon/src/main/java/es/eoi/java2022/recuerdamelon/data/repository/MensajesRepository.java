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

//    List<Mensajes> findByUsersAndRecieved (List<User> users, boolean recieved);
//    Page<Mensajes> findByUserIdAndDeleted (Integer id, boolean deleted, Pageable pageable);
//    Page<Mensajes> findByUserIdAndSaved (Integer id, boolean saved, Pageable pageable);
//    Page<Mensajes> findByUserIdAndSent (Integer id, boolean sent, Pageable pageable);
//    Page<Mensajes> findByUserIdAndInvitation (Integer id, boolean invitation, Pageable pageable);

}
