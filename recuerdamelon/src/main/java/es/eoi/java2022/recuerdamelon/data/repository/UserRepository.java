package es.eoi.java2022.recuerdamelon.data.repository;


import es.eoi.java2022.recuerdamelon.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsernameAndActiveTrue(String name);
    User findByEmailIgnoreCase(String email);
}
