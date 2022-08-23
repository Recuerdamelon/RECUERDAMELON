package es.eoi.java2022.recuerdamelon.service;

import es.eoi.java2022.recuerdamelon.data.repository.MessagesRepository;
import es.eoi.java2022.recuerdamelon.dto.MessagesDTO;
import es.eoi.java2022.recuerdamelon.data.entity.ChatMessage;
import es.eoi.java2022.recuerdamelon.service.mapper.MessagesServiceMapper;
import org.springframework.stereotype.Service;

@Service
public class MessagesService {
    private final MessagesRepository repository;
    private final MessagesServiceMapper mapper;

    public MessagesService(MessagesRepository repository, MessagesServiceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    //Metodo para guardar datos
    public ChatMessage save(MessagesDTO messagesDTO) {
        if (messagesDTO.getId() != null){
            throw new RuntimeException("Conflicto de tipo id");
        }
        return repository.save(mapper.toEntity(messagesDTO));
    }


    //Metodo para el listado principal
//    public Page<MessagesDTO> findByUserId(Integer userId, Pageable pageable) {
//        return getRepository().findByUserId(userId, pageable).map(getServiceMapper()::toDto);
//    }
}
