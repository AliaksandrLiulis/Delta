package by.delta.service.impl;

import by.delta.converter.impl.FaceConverter;
import by.delta.converter.impl.MessageConverter;
import by.delta.dto.MessageDto;
import by.delta.dto.UserDto;
import by.delta.exception.ValidationException;
import by.delta.exception.errorCode.ServiceErrorCode;
import by.delta.model.User;
import by.delta.repository.impl.FaceRepositoryImpl;
import by.delta.repository.impl.MessageRepositoryImpl;
import by.delta.service.IMessageService;
import by.delta.service.IUserService;
import by.delta.specification.impl.face.GetFaceByFaceId;
import by.delta.specification.impl.face.GetFaceByUser;
import by.delta.util.ConstParamService;
import by.delta.util.Helper;
import by.delta.validator.MessageValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class MessageService implements IMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private MessageConverter messageConverter;
    @Autowired
    private MessageValidator messageValidator;
    @Autowired
    private MessageRepositoryImpl messageRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private FaceRepositoryImpl faceRepository;
    @Autowired
    private FaceConverter faceConverter;

    @Override
    @Transactional
    public MessageDto createMessage(Authentication authentication, MessageDto messageDto) {
        messageValidator.validate(messageDto);
        messageDto.setCreateDate(LocalDate.now());
        Long idUser = getUserbByAuntification(authentication).getId();
        messageDto.setFaceDto(faceConverter.modelToDto(faceRepository.query(new GetFaceByUser(Helper.getWraperId(idUser)), 1,0).get(0)));
        return messageConverter.modelToDto(messageRepository.save(messageConverter.dtoToModel(messageDto)));
    }

    private User getUserbByAuntification(Authentication authentication){
        List<User> users =  userService.getUserByEmail(authentication.getName());
        if (CollectionUtils.isEmpty(users)){
            LOGGER.error("User with such e-mail not exist");
            throw new ValidationException(ServiceErrorCode.EMAIL_USER_NOT_EXISTS, ConstParamService.USER_EMAIL_STRING);
        }
        return users.get(0);
    }
}