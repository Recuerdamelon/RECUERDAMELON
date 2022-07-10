package es.eoi.java2022.recuerdamelon.service.mapper;

import es.eoi.java2022.recuerdamelon.data.entity.Calendar;
import es.eoi.java2022.recuerdamelon.dto.CalendarDTO;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
@Mapper(componentModel = "spring")
public interface CalendarServiceMapper extends IEntityMapper<Calendar, CalendarDTO> {
}
