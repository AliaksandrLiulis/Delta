package by.delta.ExtraClasses;

import by.delta.converter.impl.MessageConverter;
import by.delta.dto.FaceDto;
import by.delta.dto.MessageDto;
import by.delta.model.Message;
import by.delta.repository.impl.MessageRepositoryImpl;
import by.delta.service.IFaceService;
import by.delta.service.IMessageService;
import by.delta.specification.impl.message.GetUserMessagesByFaceId;
import by.delta.util.Helper;
import by.delta.validator.MessageValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

//@Service
public class MessageService implements IMessageService {

//    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

//    @Autowired
//    private MessageConverter messageConverter;
//    @Autowired
//    private MessageValidator messageValidator;
//    @Autowired
//    private MessageRepositoryImpl messageRepository;
//    @Autowired
//    private IFaceService faceService;

    //    @Override
//    @Transactional
    public MessageDto createMessage(Authentication authentication, MessageDto messageDto) {
//        messageValidator.validate(messageDto);
//        messageDto.setCreateDate(LocalDate.now());
//        messageDto.setFaceDto(faceService.getFaceByUserEmail(authentication.getName()));
//        return messageConverter.modelToDto(messageRepository.save(messageConverter.dtoToModel(messageDto)));
        return null;
    }

    //    @Override
    public Set<MessageDto> getUserMessages(Authentication authentication) {
//        FaceDto faceDto = faceService.getFaceByUserEmail(authentication.getName());
//        List<Message> messages = messageRepository.query(new GetUserMessagesByFaceId(Helper.getWraperId(faceDto.getId())), 100, 0);
//        return messageConverter.modelToDtoList(new LinkedHashSet<>(messages));
        return null;
    }
}