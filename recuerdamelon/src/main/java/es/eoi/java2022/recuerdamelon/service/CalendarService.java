package es.eoi.java2022.recuerdamelon.service;

import es.eoi.java2022.recuerdamelon.data.entity.Calendar;
import es.eoi.java2022.recuerdamelon.data.repository.CalendarRepository;
import es.eoi.java2022.recuerdamelon.dto.CalendarDTO;
import es.eoi.java2022.recuerdamelon.service.mapper.CalendarServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class CalendarService {
    private final CalendarRepository repository;
    private final CalendarServiceMapper mapper;

    public CalendarService(CalendarRepository repository, CalendarServiceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Calendar> findAll(Pageable pageable) {
        return repository.findAll(pageable).toList();
    }

    public Calendar findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("El Id %d no existe", id)));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Calendar update(Calendar calendar) {
        if (calendar.getId() == null) {
            throw new RuntimeException("No se puede actualizar tareas que no tengan Identificador");
        }
        return repository.save(calendar);
    }

    public CalendarDTO save(CalendarDTO calendarDTO) {
        if (calendarDTO.getId() != null) {
            throw new RuntimeException("El Identificador no puede ser nulo");
        }
        return mapper.toDto(repository.save(mapper.toEntity(calendarDTO)));
    }

}