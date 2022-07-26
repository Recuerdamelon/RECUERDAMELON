package es.eoi.java2022.recuerdamelon.service;



import es.eoi.java2022.recuerdamelon.data.entity.Business;
import es.eoi.java2022.recuerdamelon.data.repository.BusinessRepository;
import es.eoi.java2022.recuerdamelon.dto.BusinessDTO;
import es.eoi.java2022.recuerdamelon.service.mapper.BusinessServiceMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {
    private final BusinessRepository repository;
    private final BusinessServiceMapper mapper;

    public BusinessService(BusinessRepository repository, BusinessServiceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Business> findAll(Pageable pageable) {
        return repository.findAll(pageable).toList();
    }

    public Business findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("El Id %d no existe", id)));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Business update(Business business) {
        if (business.getId() == null) {
            throw new RuntimeException("No se puede actualizar tareas que no tengan Identificador");
        }
        return repository.save(business);
    }

    public BusinessDTO save(BusinessDTO businessDTO) {
        if (businessDTO.getId() != null) {
            throw new RuntimeException("El Identificador no puede ser nulo");
        }
        return mapper.toDto(repository.save(mapper.toEntity(businessDTO)));
    }
}
