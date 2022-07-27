package es.eoi.java2022.recuerdamelon.service;

import es.eoi.java2022.recuerdamelon.data.entity.Mensajes;
import es.eoi.java2022.recuerdamelon.data.repository.MensajesRepository;
import es.eoi.java2022.recuerdamelon.data.repository.UserRepository;
import es.eoi.java2022.recuerdamelon.dto.MensajesDTO;
import es.eoi.java2022.recuerdamelon.listeners.MessageListenerGlobalAsinc;
import es.eoi.java2022.recuerdamelon.service.mapper.MensajesServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MensajesService extends AbstractService<Mensajes, Integer, MensajesDTO,
        MensajesRepository, MensajesServiceMapper> {
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(MessageListenerGlobalAsinc.class);

    @Autowired
    protected MensajesService(MensajesRepository repository, MensajesServiceMapper serviceMapper,
                              UserRepository userRepository) {
        super(repository, serviceMapper);
        this.userRepository = userRepository;
    }

    //Metodo para guardar datos
    @Override
    public MensajesDTO save(MensajesDTO dto) {
        logger.info("Estamos guardando mensaje: " + dto.getMensaje());
        final Mensajes entity = getServiceMapper().toEntity(dto);
        entity.setUser(this.userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException(String.format("The user %s does not exist", dto.getUserId()))));
        final Mensajes savedEntity = this.getRepository().save(entity);
        return getServiceMapper().toDto(savedEntity);
    }

    @Override
    public MensajesDTO save(MensajesDTO dto, Integer usrid) {
        logger.info("Estamos guardando mensaje: " + dto.toString());
        final Mensajes entity = getServiceMapper().toEntity(dto);
        dto.setUserId(usrid);
        entity.setUser(this.userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException(String.format("The user %s does not exist", dto.getUserId()))));
        final Mensajes savedEntity = this.getRepository().save(entity);
        return getServiceMapper().toDto(savedEntity);
    }

    //Metodo para el listado principal
    public Page<MensajesDTO> findByUserId(Integer userId, Pageable pageable) {
        return getRepository().findByUserId(userId, pageable).map(getServiceMapper()::toDto);
    }
}
