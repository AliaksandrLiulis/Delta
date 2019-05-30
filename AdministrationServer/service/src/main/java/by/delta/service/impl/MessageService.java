package by.delta.service.impl;

import by.delta.converter.impl.MessageConverter;
import by.delta.dto.MessageDto;
import by.delta.repository.impl.MessageRepositoryImpl;
import by.delta.service.IMessageService;
import by.delta.validator.MessageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
public class MessageService implements IMessageService {

    @Autowired
    private MessageConverter messageConverter;
    @Autowired
    private MessageValidator messageValidator;
    @Autowired
    private MessageRepositoryImpl messageRepository;

    @Override
    @Transactional
    public MessageDto createMessage(Map<String, String> resources) {
        MessageDto messageDto = messageValidator.getMessageDtoByMapResource(resources);
        return messageConverter.modelToDto(messageRepository.save(messageConverter.dtoToModel(messageDto)));
    }
}