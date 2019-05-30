package by.delta.dto;

import by.delta.dto.abstractdto.AbstractDto;

public class AppealDto extends AbstractDto {

    private Integer appealState;

    private MessageDto messageDto;

    private IncomingDto incomingDto;

    public Integer getAppealState() {
        return appealState;
    }

    public void setAppealState(Integer appealState) {
        this.appealState = appealState;
    }

    public MessageDto getMessageDto() {
        return messageDto;
    }

    public void setMessageDto(MessageDto messageDto) {
        this.messageDto = messageDto;
    }

    public IncomingDto getIncomingDto() {
        return incomingDto;
    }

    public void setIncomingDto(IncomingDto incomingDto) {
        this.incomingDto = incomingDto;
    }

    @Override
    public String toString() {
        return "AppealDto{" +
                "appealState=" + appealState +
                ", messageDto=" + messageDto +
                ", incomingDto=" + incomingDto +
                '}';
    }
}