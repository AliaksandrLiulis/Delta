package by.delta.validator;

import by.delta.dto.MessageDto;
import by.delta.exception.errorCode.ServiceErrorCode;
import by.delta.service.IFaceService;
import by.delta.util.ConstParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Map;

@Component
public class MessageValidator {

    public void validate(final MessageDto messageDto) {
        CommonValidator.checkNullObject(messageDto, "Message reference is null", ServiceErrorCode.MESSAGE_REFERENCE_IS_NULL, ConstParamService.MESSAGE_STRING);
        if (messageDto.getMessageSubject() != null) {
            messageDto.setMessageSubject(messageDto.getMessageSubject().trim());
        }
        if (messageDto.getMessageBody() != null) {
            messageDto.setMessageBody(messageDto.getMessageBody().trim());
        }
        checkMessageSubject(messageDto);
        checkMessageBody(messageDto);
    }

    public void checkId(final String id) {
        CommonValidator.checkNumberParams(id, "Message id is not number", ServiceErrorCode.ID_MESSAGE_NOT_NUMBER, ConstParamService.MESSAGE_ID_STRING);
        Long longId = Long.parseLong(id);
        CommonValidator.checkIdNullParams(longId, "Message id is null", ServiceErrorCode.ID_MESSAGE_IS_NULL, ConstParamService.MESSAGE_ID_STRING);
        CommonValidator.checkIdLengthParams(longId, 10, "Message Id is long so much", ServiceErrorCode.ID_MESSAGE_IS_LONG_SO_MUCH, ConstParamService.MESSAGE_ID_STRING);
        CommonValidator.checkIdLessParams(longId, 0, "Message id is less than 0", ServiceErrorCode.ID_MESSAGE_IS_LESS_THAN_0, ConstParamService.MESSAGE_ID_STRING);
        CommonValidator.checkIdEqualsParams(longId, 0, "Message id equals 0", ServiceErrorCode.ID_MESSAGE_IS_EQUALS_0, ConstParamService.MESSAGE_ID_STRING);
    }

    private void checkMessageSubject(final MessageDto messageDto) {
        CommonValidator.checkNullStringParams(messageDto.getMessageSubject(), "subject of message is null", ServiceErrorCode.MESSAGE_SUBJECT_IS_NULL, ConstParamService.SUBJECT_STRING);
        CommonValidator.checkEmptyStringParams(messageDto.getMessageSubject(), "subject of message is Empty", ServiceErrorCode.MESSAGE_SUBJECT_IS_EMPTY, ConstParamService.SUBJECT_STRING);
        CommonValidator.checkLengthStringParams(messageDto.getMessageSubject(), "subject of message is long so much", 100, ServiceErrorCode.MESSAGE_SUBJECT_IS_LONG_SO_MUCH, ConstParamService.SUBJECT_STRING);
    }

    private void checkMessageBody(final MessageDto messageDto) {
        CommonValidator.checkNullStringParams(messageDto.getMessageBody(), "body of message is null", ServiceErrorCode.MESSAGE_BODY_IS_NULL, ConstParamService.BODY_STRING);
        CommonValidator.checkEmptyStringParams(messageDto.getMessageBody(), "body of message is Empty", ServiceErrorCode.MESSAGE_BODY_IS_EMPTY, ConstParamService.BODY_STRING);
        CommonValidator.checkLengthStringParams(messageDto.getMessageBody(), "body of message is long so much", 3000, ServiceErrorCode.MESSAGE_SUBJECT_IS_LONG_SO_MUCH, ConstParamService.SUBJECT_STRING);
    }


//    private void checkMessageReply(final MessageDto messageDto) {
//        CommonValidator.checkIdNullParams(messageDto.getReplyMessage(), "reply id message is null", ServiceErrorCode.REPLY_MESSAGE_IS_NULL, ConstParamService.REPLY_STRING);
//        CommonValidator.checkNumberParams(messageDto.getReplyMessage().toString(), "reply id message isn't number", ServiceErrorCode.REPLY_MESSAGE_IS_NOT_NUMBER, ConstParamService.REPLY_STRING);
//    }
}