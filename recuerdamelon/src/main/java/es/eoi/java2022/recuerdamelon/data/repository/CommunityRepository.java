package es.eoi.java2022.recuerdamelon.data.repository;

import es.eoi.java2022.recuerdamelon.data.entity.Community;
import es.eoi.java2022.recuerdamelon.data.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommunityRepository extends JpaRepository<Community, Integer> {

    Community findByName (String name);
}
