package by.delta.model;

import by.delta.model.abstractmodel.AbstractModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
@Table(name = "attachments")
public class Attachment extends AbstractModel {

    @Column(name = "file_name", length = 100)
    private String fileName;
    @Column(name = "file_path", length = 100)
    private String filePath;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    private Message message;

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

        Attachment that = (Attachment) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(fileName, that.fileName)
                .append(filePath, that.filePath)
                .append(message, that.message)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(fileName)
                .append(filePath)
                .append(message)
                .toHashCode();
    }
}