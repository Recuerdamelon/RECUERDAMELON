package es.eoi.java2022.recuerdamelon.utils;

import es.eoi.java2022.recuerdamelon.dto.HorarioDTO;

import java.util.*;

public  class TaskHorario {

    public  static Map<String, String> map(List<String> start, List<String> end){
        Map<String,String> map = new HashMap<String, String>();
        for (int i = 0; i < start.size(); i++) {
            map.put(start.get(i),end.get(i));
        }
        return map;

    }
    public static List<String> horarios (HorarioDTO horarioDTO){
        List<String> tareas = new ArrayList<>();
        tareas.add(horarioDTO.getTask1());
        if (!Objects.equals(horarioDTO.getTask2(), "")) {
            tareas.add(horarioDTO.getTask2());
        }
        if (!Objects.equals(horarioDTO.getTask3(), "")) {
            tareas.add(horarioDTO.getTask3());
        }
        if (!Objects.equals(horarioDTO.getTask4(), "")) {
            tareas.add(horarioDTO.getTask4());
        }
        if (!Objects.equals(horarioDTO.getTask5(), "")) {
            tareas.add(horarioDTO.getTask5());
        }
        if (!Objects.equals(horarioDTO.getTask6(), "")) {
            tareas.add(horarioDTO.getTask6());
        }
        if (!Objects.equals(horarioDTO.getTask7(), "")) {
            tareas.add(horarioDTO.getTask7());
        }
        if (!Objects.equals(horarioDTO.getTask8(), "")) {
            tareas.add(horarioDTO.getTask8());
        }
        return tareas;
    }
    public static List<String> starTime (HorarioDTO horarioDTO){
        List<String> starTime = new ArrayList<>();
        starTime.add(horarioDTO.getStartLocalDateTime1());
        if (!Objects.equals(horarioDTO.getStartLocalDateTime2(), "")) {
            starTime.add(horarioDTO.getStartLocalDateTime2());
        }
        if (!Objects.equals(horarioDTO.getStartLocalDateTime3(), "")) {
            starTime.add(horarioDTO.getStartLocalDateTime3());
        }
        if (!Objects.equals(horarioDTO.getStartLocalDateTime4(), "")) {
            starTime.add(horarioDTO.getStartLocalDateTime4());
        }
        if (!Objects.equals(horarioDTO.getStartLocalDateTime5(), "")) {
            starTime.add(horarioDTO.getStartLocalDateTime5());
        }
        if (!Objects.equals(horarioDTO.getStartLocalDateTime6(), "")) {
            starTime.add(horarioDTO.getStartLocalDateTime6());
        }
        if (!Objects.equals(horarioDTO.getStartLocalDateTime7(), "")) {
            starTime.add(horarioDTO.getStartLocalDateTime7());
        }
        if (!Objects.equals(horarioDTO.getStartLocalDateTime8(), "")) {
            starTime.add(horarioDTO.getStartLocalDateTime8());
        }
        return starTime;
    }
    public static List<String> endTime (HorarioDTO horarioDTO){
        List<String> endTime = new ArrayList<>();
        endTime.add(horarioDTO.getEndLocalDateTime1());
        if (!Objects.equals(horarioDTO.getEndLocalDateTime2(), "")) {
            endTime.add(horarioDTO.getEndLocalDateTime2());
        }
        if (!Objects.equals(horarioDTO.getEndLocalDateTime3(), "")) {
            endTime.add(horarioDTO.getEndLocalDateTime3());
        }
        if (!Objects.equals(horarioDTO.getEndLocalDateTime4(), "")) {
            endTime.add(horarioDTO.getEndLocalDateTime4());
        }
        if (!Objects.equals(horarioDTO.getEndLocalDateTime5(), "")) {
            endTime.add(horarioDTO.getEndLocalDateTime5());
        }
        if (!Objects.equals(horarioDTO.getEndLocalDateTime6(), "")) {
            endTime.add(horarioDTO.getEndLocalDateTime6());
        }
        if (!Objects.equals(horarioDTO.getEndLocalDateTime7(), "")) {
            endTime.add(horarioDTO.getEndLocalDateTime7());
        }
        if (!Objects.equals(horarioDTO.getEndLocalDateTime8(), "")) {
            endTime.add(horarioDTO.getEndLocalDateTime8());
        }
        return endTime;
    }

}
