package es.eoi.java2022.recuerdamelon.service;

import es.eoi.java2022.recuerdamelon.data.entity.Calendar;
import es.eoi.java2022.recuerdamelon.data.repository.CalendarRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CalendarService {
    private final CalendarRepository repository;

    public CalendarService(CalendarRepository repository) {
        this.repository = repository;
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

    public Calendar save(Calendar calendar) {
        if (calendar.getId() != null) {
            throw new RuntimeException("El Identificador no puede ser nulo");
        }
        return repository.save(calendar);
    }

}