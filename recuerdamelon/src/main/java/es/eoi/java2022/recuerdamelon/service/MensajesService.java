package es.eoi.java2022.recuerdamelon.service;

import es.eoi.java2022.recuerdamelon.data.entity.Mensajes;
import es.eoi.java2022.recuerdamelon.data.entity.Task;
import es.eoi.java2022.recuerdamelon.data.entity.User;
import es.eoi.java2022.recuerdamelon.data.repository.MensajesRepository;
import es.eoi.java2022.recuerdamelon.data.repository.UserRepository;
import es.eoi.java2022.recuerdamelon.dto.MensajesDTO;
import es.eoi.java2022.recuerdamelon.dto.MessagesDTO;
import es.eoi.java2022.recuerdamelon.service.mapper.MensajesServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensajesService {
    private final UserRepository userRepository;
    private final MensajesServiceMapper serviceMapper;
    private final MensajesRepository repository;


    protected MensajesService(MensajesRepository repository, MensajesServiceMapper serviceMapper,
                              UserRepository userRepository) {
        this.userRepository = userRepository;
        this.serviceMapper = serviceMapper;
        this.repository = repository;
    }

    public List<Mensajes> findAll(Pageable pageable) {
        return repository.findAll(pageable).toList();

    }

    public Mensajes save(MensajesDTO dto) {

            return this.repository.save(serviceMapper.toEntity(dto)); //Guarde los ids de quienes reciben el mensaje

    }

    //Metodo para el listado principal
    public List<Mensajes> findByUsersId(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(String.format("El Id %d no existe", userId)))
                .getMensajes().stream().collect(Collectors.toList());
    }

    public Page<MensajesDTO> findByRecieverAndRecieved(Integer reciever, boolean recieved,  Pageable pageable) {
        return repository.findByRecieverAndRecieved(reciever, recieved, pageable).map(serviceMapper::toDto);
    }
    public Page<MensajesDTO> findByRecieverAndDeleted(Integer reciever, boolean deleted,  Pageable pageable) {
        return repository.findByRecieverAndDeleted(reciever, deleted, pageable).map(serviceMapper::toDto);
    }
    public Page<MensajesDTO> findByRecieverAndSaved(Integer reciever, boolean saved,  Pageable pageable) {
        return repository.findByRecieverAndSaved(reciever, saved, pageable).map(serviceMapper::toDto);
    }
    public Page<MensajesDTO> findByRecieverAndSent(Integer reciever, boolean sent,  Pageable pageable) {
        return repository.findByRecieverAndSent(reciever, sent, pageable).map(serviceMapper::toDto);
    }


}
