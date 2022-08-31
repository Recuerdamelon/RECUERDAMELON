package es.eoi.java2022.recuerdamelon.service;


import es.eoi.java2022.recuerdamelon.data.entity.Community;
import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.data.repository.CommunityRepository;
import es.eoi.java2022.recuerdamelon.data.repository.UserRepository;
import es.eoi.java2022.recuerdamelon.dto.CommunityDTO;
import es.eoi.java2022.recuerdamelon.service.mapper.CommunityServiceMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommunityService {

    UserRepository userRepository;
    CommunityRepository repository;
    CommunityServiceMapper mapper;

    public CommunityService(CommunityRepository repository, CommunityServiceMapper mapper, UserRepository userRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    public List<Community> findAll(Pageable pageable) {
        return repository.findAll(pageable).toList();
    }

    public List<User> findFriends(Integer idCommunity){
        return repository.findById(idCommunity)
                .orElseThrow(() -> new RuntimeException(String.format("El Id %d no existe", idCommunity)))
                .getUsers().stream().collect(Collectors.toList());
    }

    public Community findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("El Id %d no existe", id)));
    }

    public Community findByName(String name) {
        return repository.findByName(name);
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
    public Community save(CommunityDTO communityDTO) {
        return repository.save(mapper.toEntity(communityDTO));
    }

//    public Page<CommunityDTO> findByUserId(Integer userId, Pageable pageable) {
//        return repository.findByUserId(userId, pageable).map(mapper::toDto);
//    }
}
