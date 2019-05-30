package by.delta.dto;

import by.delta.dto.abstractdto.AbstractDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDto extends AbstractDto {

    private static final String CREATE_DATE_FORMAT = "yyyy-MM-dd";
    private static final String SEND_DATE_FORMAT = "yyyy-MM-dd";

    private String messageSubject;

    private String messageBody;

    private Long replyMessage;

    @JsonFormat(pattern = CREATE_DATE_FORMAT)
    private LocalDate createDate;

    @JsonFormat(pattern = SEND_DATE_FORMAT)
    private LocalDate sendDate;

    private FaceDto faceDto;

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Long getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(Long replyMessage) {
        this.replyMessage = replyMessage;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDate sendDate) {
        this.sendDate = sendDate;
    }

    public FaceDto getFaceDto() {
        return faceDto;
    }

    public void setFaceDto(FaceDto faceDto) {
        this.faceDto = faceDto;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "messageSubject='" + messageSubject + '\'' +
                ", messageBody='" + messageBody + '\'' +
                ", replyMessage=" + replyMessage +
                ", createDate=" + createDate +
                ", sendDate=" + sendDate +
                ", faceDto=" + faceDto +
                '}';
    }
}