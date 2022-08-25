package es.eoi.java2022.recuerdamelon.dto.service.mapper;

import es.eoi.java2022.recuerdamelon.data.entity.Calendar;
import es.eoi.java2022.recuerdamelon.dto.CalendarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CalendarServiceMapper extends IEntityMapper<Calendar, CalendarDTO> {
}
