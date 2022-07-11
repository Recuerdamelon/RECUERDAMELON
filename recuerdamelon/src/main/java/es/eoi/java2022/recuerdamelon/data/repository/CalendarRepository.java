package es.eoi.java2022.recuerdamelon.data.repository;


import es.eoi.java2022.recuerdamelon.data.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar,Integer> {

}
