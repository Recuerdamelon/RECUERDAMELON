package es.eoi.java2022.recuerdamelon.service;


import es.eoi.java2022.recuerdamelon.data.entity.Community;
import es.eoi.java2022.recuerdamelon.data.repository.CommunityRepository;
import es.eoi.java2022.recuerdamelon.dto.CommunityDTO;
import es.eoi.java2022.recuerdamelon.service.mapper.CommunityServiceMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityService {

    CommunityRepository repository;
    CommunityServiceMapper mapper;

    public CommunityService(CommunityRepository repository, CommunityServiceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Community> findAll(Pageable pageable) {
        return repository.findAll(pageable).toList();
    }

    public Community findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("El Id %d no existe", id)));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    public Community update(Community community) {
        if (community.getId() == null){
            throw new RuntimeException("No se puede actualizar tareas que no tengan Identificador");
        }
        return repository.save(community);
    }
    public CommunityDTO save(CommunityDTO communityDTO) {
        if (communityDTO.getId() != null){
            throw new RuntimeException("El Identificador no puede ser nulo");
        }
        return mapper.toDto(repository.save(mapper.toEntity(communityDTO)));
    }
}
