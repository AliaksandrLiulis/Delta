package by.delta.model;

import by.delta.model.abstractmodel.AbstractModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
@Table(name = "Appeals")
public class Appeal extends AbstractModel {

    @Column(name = "appeal_state")
    private Integer appealState;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    private Message message;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incoming_id")
    private Incoming incoming;

    public Integer getAppealState() {
        return appealState;
    }

    public void setAppealState(Integer appealState) {
        this.appealState = appealState;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Incoming getIncoming() {
        return incoming;
    }

    public void setIncoming(Incoming incoming) {
        this.incoming = incoming;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Appeal appeal = (Appeal) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(appealState, appeal.appealState)
                .append(message, appeal.message)
                .append(incoming, appeal.incoming)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(appealState)
                .append(message)
                .append(incoming)
                .toHashCode();
    }
}