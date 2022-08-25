package es.eoi.java2022.recuerdamelon.dto.service.mapper;

public interface IEntityMapper<E, DTO> {
    DTO toDto(E e);

    E toEntity(DTO dto);
}
