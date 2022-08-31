package es.eoi.java2022.recuerdamelon.data.repository;


import es.eoi.java2022.recuerdamelon.data.entity.Community;
import es.eoi.java2022.recuerdamelon.data.entity.Task;
import es.eoi.java2022.recuerdamelon.data.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Integer> {

    //FINDALL
    User findByUsernameAndActiveTrue(String name);
    User findByEmailIgnoreCase(String email);
    User findByUsername(String username);
}
