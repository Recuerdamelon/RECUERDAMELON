package es.eoi.java2022.recuerdamelon.service.mapper;

import es.eoi.java2022.recuerdamelon.data.entity.Calendar;
import es.eoi.java2022.recuerdamelon.dto.CalendarDTO;
import es.eoi.java2022.recuerdamelon.utils.DateUtil;
import org.springframework.stereotype.Service;


@Service
public class CalendarServiceMapper extends AbstractServiceMapper<Calendar, CalendarDTO> {
    public Calendar toEntity(CalendarDTO dto){
        final Calendar entity = new Calendar();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setTaskDate(DateUtil.stringToDate(dto.getTaskDate()));
        return entity;
    }
    public CalendarDTO toDto(Calendar entity){
        final CalendarDTO dto = new CalendarDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setTaskDate(DateUtil.dateToString(entity.getTaskDate()));
        return dto;
    }
}
