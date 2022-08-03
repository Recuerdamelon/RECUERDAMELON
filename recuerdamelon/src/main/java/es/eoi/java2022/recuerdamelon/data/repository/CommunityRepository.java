package es.eoi.java2022.recuerdamelon.data.repository;

import es.eoi.java2022.recuerdamelon.data.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Integer> {
}
