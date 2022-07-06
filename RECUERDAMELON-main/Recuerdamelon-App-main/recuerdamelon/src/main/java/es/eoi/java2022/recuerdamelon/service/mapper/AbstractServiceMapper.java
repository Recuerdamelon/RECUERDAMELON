package es.eoi.java2022.recuerdamelon.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractServiceMapper<E, DTO> {
        public abstract E toEntity(DTO dto);

        public abstract DTO toDto(E e);

        public List<E> toEntity(List<DTO> dtos) {
            return dtos.stream().map(this::toEntity).collect(Collectors.toList());
        }

        public List<DTO> toDto(List<E> entities) {
            return entities.stream().map(this::toDto).collect(Collectors.toList());
        }
}
