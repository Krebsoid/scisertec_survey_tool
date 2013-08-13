package de.scisertec.admin.mailtask.model.view;

import de.scisertec.admin.mailtask.model.MailTaskStatus;
import org.joda.time.DateTime;

public class MailTaskSummaryBean implements MailTaskSummary {

    Long id;

    String comment;
    String topic;
    Boolean contentAvailable;

    MailTaskStatus status;

    DateTime creationDate;

    Integer mailJobCount;

    public MailTaskSummaryBean(Long id, String comment, String topic, Boolean contentAvailable, Integer mailJobCount, DateTime creationDate, MailTaskStatus status) {
        this.id = id;
        this.comment = comment;
        this.topic = topic;
        this.contentAvailable = contentAvailable;
        this.mailJobCount = mailJobCount;
        this.creationDate = creationDate;
        this.status = status;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public Boolean getContentAvailable() {
        return contentAvailable;
    }

    @Override
    public DateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public Integer getMailJobNumber() {
        return mailJobCount;
    }

    @Override
    public MailTaskStatus getStatus() {
        return status;
    }
}
