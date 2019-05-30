package by.delta.model;

import by.delta.model.abstractmodel.AbstractModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "incomings")
public class Incoming extends AbstractModel {

    @Column(name = "topic_message")
    private Integer topicMessage;
    @Column(name = "message_state")
    private Integer messageState;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_code_face_id")
    private Face face;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    private Message message;
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

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Incoming incoming = (Incoming) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(topicMessage, incoming.topicMessage)
                .append(messageState, incoming.messageState)
                .append(face, incoming.face)
                .append(message, incoming.message)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(topicMessage)
                .append(messageState)
                .append(face)
                .append(message)
                .toHashCode();
    }
}