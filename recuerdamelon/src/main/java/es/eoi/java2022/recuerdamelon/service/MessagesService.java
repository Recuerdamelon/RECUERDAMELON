package es.eoi.java2022.recuerdamelon.service;

import es.eoi.java2022.recuerdamelon.data.entity.Message;
import es.eoi.java2022.recuerdamelon.data.repository.MessagesRepository;
import es.eoi.java2022.recuerdamelon.data.repository.UserRepository;
import es.eoi.java2022.recuerdamelon.dto.MessagesDTO;
import es.eoi.java2022.recuerdamelon.listeners.MessageListenerGlobalAsinc;
import es.eoi.java2022.recuerdamelon.service.mapper.MessagesServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessagesService extends AbstractService<Message, Integer, MessagesDTO,
        MessagesRepository, MessagesServiceMapper> {
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(MessageListenerGlobalAsinc.class);

    @Autowired
    protected MessagesService(MessagesRepository repository, MessagesServiceMapper serviceMapper,
                              UserRepository userRepository) {
        super(repository, serviceMapper);
        this.userRepository = userRepository;
    }

    //Metodo para guardar datos
    @Override
    public MessagesDTO save(MessagesDTO dto) {
        logger.info("Estamos guardando mensaje: " + dto.getMessage());
        final Message entity = getServiceMapper().toEntity(dto);
        entity.setUser(this.userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException(String.format("The user %s does not exist", dto.getUserId()))));
        final Message savedEntity = this.getRepository().save(entity);
        return getServiceMapper().toDto(savedEntity);
    }

    @Override
    public MessagesDTO save(MessagesDTO dto, Integer usrid) {
        logger.info("Estamos guardando mensaje: " + dto.toString());
        final Message entity = getServiceMapper().toEntity(dto);
        dto.setUserId(usrid);
        entity.setUser(this.userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException(String.format("The user %s does not exist", dto.getUserId()))));
        final Message savedEntity = this.getRepository().save(entity);
        return getServiceMapper().toDto(savedEntity);
    }

    //Metodo para el listado principal
    public Page<MessagesDTO> findByUserId(Integer userId, Pageable pageable) {
        return getRepository().findByUserId(userId, pageable).map(getServiceMapper()::toDto);
    }
}
