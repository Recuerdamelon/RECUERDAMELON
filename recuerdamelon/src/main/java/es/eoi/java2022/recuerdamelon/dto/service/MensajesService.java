package es.eoi.java2022.recuerdamelon.dto.service;

import es.eoi.java2022.recuerdamelon.data.entity.Mensajes;
import es.eoi.java2022.recuerdamelon.data.repository.MensajesRepository;
import es.eoi.java2022.recuerdamelon.data.repository.UserRepository;
import es.eoi.java2022.recuerdamelon.dto.MensajesDTO;
import es.eoi.java2022.recuerdamelon.dto.service.mapper.MensajesServiceMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MensajesService {
    private final UserRepository userRepository;
    private final MensajesServiceMapper serviceMapper;
    private final MensajesRepository repository;
    private final UserService userService;


    protected MensajesService(MensajesRepository repository, MensajesServiceMapper serviceMapper,
                              UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.serviceMapper = serviceMapper;
        this.repository = repository;
        this.userService = userService;
    }

    public List<Mensajes> findAll(Pageable pageable) {
        return repository.findAll(pageable).toList();

    }

    public Mensajes findById (Integer id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("El Id %d no existe", id)));
    }

    public Mensajes save(MensajesDTO dto) {

            return this.repository.save(serviceMapper.toEntity(dto)); //Guarde los ids de quienes reciben el mensaje

    }

    public void delete (Mensajes mensajes){
        this.repository.delete(mensajes);
    }

    public List<Mensajes> findByRecieved(Integer id, boolean recieved) {
        List<Mensajes> all = userService.findMensajesByUserId(id);
        List<Mensajes> messagesRecieved = new ArrayList<>();
        for (Mensajes m:all) {
            if(m.isRecieved())
                messagesRecieved.add(m);
        }
        return messagesRecieved;
    }

    public List<Mensajes> findBySent(Integer id, boolean sent) {
        List<Mensajes> all = userService.findMensajesByUserId(id);
        List<Mensajes> messagesSent = new ArrayList<>();
        for (Mensajes m:all) {
            if(m.isSent())
                messagesSent.add(m);
        }
        return messagesSent;
    }

    public List<Mensajes> findByDeleted(Integer id, boolean deleted) {
        List<Mensajes> all = userService.findMensajesByUserId(id);
        List<Mensajes> messagesDeleted = new ArrayList<>();
        for (Mensajes m : all) {
            if (m.isDeleted())
                messagesDeleted.add(m);
        }
        return messagesDeleted;
    }


    public List<Mensajes> findBySaved(Integer id, boolean saved) {
        List<Mensajes> all = userService.findMensajesByUserId(id);
        List<Mensajes> messagesSaved = new ArrayList<>();
        for (Mensajes m:all) {
            if(m.isSaved())
                messagesSaved.add(m);
        }
        return messagesSaved;
    }

    public List<Mensajes> findByInvitation(Integer id, boolean recieved) {
        List<Mensajes> all = userService.findMensajesByUserId(id);
        List<Mensajes> messagesInvitation = new ArrayList<>();
        for (Mensajes m:all) {
            if(m.isInvitation())
                messagesInvitation.add(m);
        }
        return messagesInvitation;
    }


//    public Page<MensajesDTO> findByUserIdAndDeleted(Integer userId, boolean deleted,  Pageable pageable) {
//        return repository.findByUserIdAndDeleted(userId, deleted, pageable).map(serviceMapper::toDto);
//    }
//    public Page<MensajesDTO> findByUserIdAndSaved(Integer userId, boolean saved,  Pageable pageable) {
//        return repository.findByUserIdAndSaved(userId, saved, pageable).map(serviceMapper::toDto);
//    }
//    public Page<MensajesDTO> findByUserIdAndSent(Integer userId, boolean sent,  Pageable pageable) {
//        return repository.findByUserIdAndSent(userId, sent, pageable).map(serviceMapper::toDto);
//    }
//
//    public Page<MensajesDTO> findByUserIdAndInvitation(Integer userId, boolean invitation,  Pageable pageable) {
//        return repository.findByUserIdAndInvitation(userId, invitation, pageable).map(serviceMapper::toDto);
//    }


}
