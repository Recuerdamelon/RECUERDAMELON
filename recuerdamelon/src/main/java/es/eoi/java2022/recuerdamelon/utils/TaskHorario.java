package es.eoi.java2022.recuerdamelon.utils;

import es.eoi.java2022.recuerdamelon.dto.HorarioDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public  class TaskHorario {

    public  static Map<String, String> map(List<String> start, List<String> end){
        Map<String,String> map = new HashMap<String, String>();
        for (int i = 0; i < start.size(); i++) {
            map.put(start.get(i),end.get(i));
        }
      return map;

    }
}
