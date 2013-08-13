package de.scisertec.admin.mailtask.model.view;

import de.scisertec.admin.mailtask.model.MailJob;
import de.scisertec.admin.mailtask.model.MailTaskStatus;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class MailTaskDetailedBean implements MailTaskDetailed {

    Long id;

    String comment;
    String topic;
    String senderName;
    String senderAddress;
    Boolean contentAvailable;

    MailTaskStatus status;

    List<MailJobEntry> mailJobContentList;

    DateTime creationDate;

    public MailTaskDetailedBean(Long id, String comment, String topic, String senderName, String senderAddress, Boolean contentAvailable,
                                DateTime creationDate, MailTaskStatus status, List<MailJob> mailJobList ) {
        this.id = id;
        this.comment = comment;
        this.topic = topic;
        this.senderName = senderName;
        this.senderAddress = senderAddress;
        this.contentAvailable = contentAvailable;
        this.status = status;
        this.creationDate = creationDate;

        ArrayList<MailJobEntry> jobContents = new ArrayList<MailJobEntry>();
        for(MailJob mailJob : mailJobList) {
            MailJobEntry mailJobEntry = mailJob.asEntry();
            jobContents.add(mailJobEntry);
        }
        this.mailJobContentList = jobContents;
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
    public String getSenderName() {
        return senderName;
    }

    @Override
    public String getSenderAddress() {
        return senderAddress;
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
    public MailTaskStatus getStatus() {
        return status;
    }

    @Override
    public List<MailJobEntry> getMailJobs() {
        return mailJobContentList;
    }

}
