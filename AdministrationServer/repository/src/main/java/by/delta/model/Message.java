package by.delta.model;

import by.delta.model.abstractmodel.AbstractModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message extends AbstractModel {

    @Column(name = "message_subject", length = 100)
    private String messageSubject;
    @Column(name = "message_body", length = 3000)
    private String messageBody;
    @Column(name = "reply_message")
    private Long replyMessage;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "send_date")
    private LocalDateTime sendDate;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "face_id")
    private Face face;

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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(messageSubject, message.messageSubject)
                .append(messageBody, message.messageBody)
                .append(replyMessage, message.replyMessage)
                .append(createDate, message.createDate)
                .append(sendDate, message.sendDate)
                .append(face, message.face)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(messageSubject)
                .append(messageBody)
                .append(replyMessage)
                .append(createDate)
                .append(sendDate)
                .append(face)
                .toHashCode();
    }
}