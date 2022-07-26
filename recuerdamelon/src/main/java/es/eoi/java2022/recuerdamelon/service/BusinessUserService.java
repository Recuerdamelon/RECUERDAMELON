package es.eoi.java2022.recuerdamelon.service;


import es.eoi.java2022.recuerdamelon.data.entity.BusinessUser;
import es.eoi.java2022.recuerdamelon.data.repository.BusinessUserRepository;
import es.eoi.java2022.recuerdamelon.dto.BusinessUserDTO;
import es.eoi.java2022.recuerdamelon.service.mapper.BusinessUserServiceMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessUserService {
    private final BusinessUserRepository repository;
    private final BusinessUserServiceMapper mapper;

    public BusinessUserService(BusinessUserRepository repository, BusinessUserServiceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<BusinessUser> findAll(Pageable pageable) {
        return repository.findAll(pageable).toList();
    }

    public BusinessUser findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("El Id %d no existe", id)));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public BusinessUser update(BusinessUser businessUser) {
        if (businessUser.getTipoTrabajador() == null) {
            throw new RuntimeException("No se puede actualizar tareas que no tengan Identificador");
        }
        return repository.save(businessUser);
    }

    public BusinessUserDTO save(BusinessUserDTO businessUserDTO) {
        if (businessUserDTO.getIdTrabajador() != null) {
            throw new RuntimeException("El Identificador no puede ser nulo");
        }
        return mapper.toDto(repository.save(mapper.toEntity(businessUserDTO)));
    }
}
