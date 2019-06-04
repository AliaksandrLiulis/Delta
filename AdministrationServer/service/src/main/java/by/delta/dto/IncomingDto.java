package by.delta.dto;

import by.delta.dto.abstractdto.AbstractDto;
import by.delta.model.Face;

public class IncomingDto extends AbstractDto {

    private Integer topicMessage;

    private Integer messageState;

    private Face face;

    private MessageDto messageDto;

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }

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

    public MessageDto getMessageDto() {
        return messageDto;
    }

    public void setMessageDto(MessageDto messageDto) {
        this.messageDto = messageDto;
    }

    @Override
    public String toString() {
        return "IncomingDto{" +
                "face=" + face +
                ", topicMessage=" + topicMessage +
                ", messageState=" + messageState +
                ", messageDto=" + messageDto +
                '}';
    }
}