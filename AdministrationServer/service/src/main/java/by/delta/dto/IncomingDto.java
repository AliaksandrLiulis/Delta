package by.delta.dto;

import by.delta.dto.abstractdto.AbstractDto;
import by.delta.model.Face;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IncomingDto extends AbstractDto {

    private Integer topicMessage;

    private Integer messageState;

    private FaceDto faceDto;

    private MessageDto messageDto;

    public Integer getTopicMessage() {
        return topicMessage;
    }

    public void setTopicMessage(Integer topicMessage) {
        this.topicMessage = topicMessage;
    }

    public Integer getMessageState() {
        return messageState;
    }

    public void setMessageState(Integer messageState) {
        this.messageState = messageState;
    }

    public FaceDto getFaceDto() {
        return faceDto;
    }

    public void setFaceDto(FaceDto faceDto) {
        this.faceDto = faceDto;
    }

    public MessageDto getMessageDto() {
        return messageDto;
    }

    public void setMessageDto(MessageDto messageDto) {
        this.messageDto = messageDto;
    }

    @Override
    public String toString() {
        return "IncomingDto{" +
                "topicMessage=" + topicMessage +
                ", messageState=" + messageState +
                ", faceDto=" + faceDto +
                ", messageDto=" + messageDto +
                '}';
    }
}