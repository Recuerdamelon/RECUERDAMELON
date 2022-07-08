package es.eoi.java2022.recuerdamelon.service.mapper;

import es.eoi.java2022.recuerdamelon.data.entity.Calendar;
import es.eoi.java2022.recuerdamelon.dto.CalendarDTO;
import es.eoi.java2022.recuerdamelon.utils.DateUtil;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;


@Mapper(componentModel = "spring")
public interface CalendarServiceMapper extends IEntityMapper<Calendar, CalendarDTO> {
}
