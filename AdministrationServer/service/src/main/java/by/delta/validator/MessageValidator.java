package by.delta.validator;

import by.delta.dto.MessageDto;
import by.delta.exception.errorCode.ServiceErrorCode;
import by.delta.service.IFaceService;
import by.delta.util.ConstParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Component
public class MessageValidator {

    @Autowired
    private IFaceService faceServiceImpl;

    public void validate(final MessageDto messageDto){

    }

    public MessageDto getMessageDtoByMapResource(final Map<String, String> resources){
        MessageDto messageDto = new MessageDto();
        CommonValidator.checkNullStringParams(resources.get(ConstParamService.MESSAGE_SUBJECT), "subject for message is null", ServiceErrorCode.MESSAGE_SUBJECT_IS_NULL, ConstParamService.SUBJECT_STRING);
        CommonValidator.checkNullStringParams(resources.get(ConstParamService.MESSAGE_BODY), "body for message is null", ServiceErrorCode.BODY_MESSAGE_IS_NULL, ConstParamService.BODY_STRING);
        CommonValidator.checkNumberParams(resources.get(ConstParamService.MESSAGE_REPLY), "reply message isn't number", ServiceErrorCode.REPLY_MESSAGE_IS_NOT_NUMBER, ConstParamService.REPLY_STRING);
        messageDto.setMessageSubject(resources.get(ConstParamService.MESSAGE_SUBJECT));
        messageDto.setMessageBody(resources.get(ConstParamService.MESSAGE_BODY));
        messageDto.setReplyMessage(Long.parseLong(resources.get(ConstParamService.MESSAGE_REPLY)));
        messageDto.setCreateDate(LocalDate.now());
        if (resources.get(ConstParamService.SEND_DATE) != null){
            messageDto.setSendDate(LocalDate.parse(resources.get(ConstParamService.SEND_DATE)));
        }
        CommonValidator.checkNumberParams(resources.get(ConstParamService.FACE_ID), "face id isn't number", ServiceErrorCode.FACE_ID_IS_NOT_NUMBER, ConstParamService.FACE_ID_STRING);
        messageDto.setFaceDto(faceServiceImpl.getFaceById(Long.parseLong(resources.get(ConstParamService.FACE_ID))));
        return  messageDto;
    }
}
