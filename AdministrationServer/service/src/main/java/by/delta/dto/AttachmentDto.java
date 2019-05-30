package by.delta.dto;

import by.delta.dto.abstractdto.AbstractDto;

public class AttachmentDto extends AbstractDto {

    private String fileName;

    private String filePath;

    private MessageDto messageDto;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public MessageDto getMessageDto() {
        return messageDto;
    }

    public void setMessageDto(MessageDto messageDto) {
        this.messageDto = messageDto;
    }

    @Override
    public String toString() {
        return "AttachmentDto{" +
                "fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", messageDto=" + messageDto +
                '}';
    }
}