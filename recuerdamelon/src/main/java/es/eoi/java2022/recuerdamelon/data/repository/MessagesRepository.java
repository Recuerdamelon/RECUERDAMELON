package es.eoi.java2022.recuerdamelon.data.repository;

import es.eoi.java2022.recuerdamelon.data.entity.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<ChatMessage, Integer> {
}
