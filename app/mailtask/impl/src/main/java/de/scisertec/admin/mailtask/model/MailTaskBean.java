package de.scisertec.admin.mailtask.model;


import de.scisertec.admin.mailtask.model.view.*;
import de.scisertec.admin.mailtask.qualifier.MailTaskPersistence;
import de.scisertec.admin.mailtask.service.MailJobCatalog;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static de.scisertec.admin.mailtask.model.MailTaskStatus.NOT_STARTED_YET;


@Entity
@Table(name = "mailtask")
public class MailTaskBean implements MailTask {

    @Id
    @GeneratedValue
    Long id;

    String comment;
    String topic;

    String senderName;
    String senderAddress;

    Boolean contentAvailable = Boolean.FALSE;

    byte[] content;

    @OrderBy("id ASC")
    @OneToMany(cascade = CascadeType.ALL)
    List<MailJobBean> mailJobs = new ArrayList<MailJobBean>();

    DateTime creationDate = new DateTime();

    @Enumerated
    MailTaskStatus status = NOT_STARTED_YET;


    @Transient
    @Inject
    MailJobCatalog mailJobCatalog;

    @Transient
    @Inject
    @MailTaskPersistence
    EntityManager entityManager;


    @Override
    public Long id() {
        return id;
    }

    @Override
    public String comment() {
        return comment;
    }

    @Override
    public String topic() {
        return topic;
    }

    @Override
    public String senderName() {
        return senderName;
    }

    @Override
    public String senderAddress() {
        return senderAddress;
    }

    @Override
    public Boolean contentAvailable() {
        return contentAvailable;
    }

    @Override
    public byte[] template() {
        return content;
    }

    @Override
    public List<MailJob> mailJobs() {
        ArrayList<MailJob> mailJobs = new ArrayList<MailJob>();
        mailJobs.addAll(this.mailJobs);
        return mailJobs;
    }

    @Override
    public DateTime creationDate() {
        return creationDate;
    }

    @Override
    public MailTaskStatus status() {
        return status;
    }



    @Override
    public MailTask update(MailTaskUpdate mailTaskUpdateRequest) {
        this.topic = mailTaskUpdateRequest.getTopic();
        this.comment = mailTaskUpdateRequest.getComment();
        this.senderName = mailTaskUpdateRequest.getSenderName();
        this.senderAddress = mailTaskUpdateRequest.getSenderAddress();
        return this;
    }

    @Override
    public MailTask status(MailTaskStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public MailTask topic(String topic) {
        this.topic = topic;
        return this;
    }

    @Override
    public MailTask comment(String comment) {
        this.comment = comment;
        return this;
    }

    @Override
    public MailTask senderName(String senderName) {
        this.senderName = senderName;
        return this;
    }

    @Override
    public MailTask senderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
        return this;
    }

    @Override
    public MailTask receiver(List<MailReceiver> mailReceiver) {
        ArrayList<MailJobBean> mailJobBeans = new ArrayList<MailJobBean>();
        for(MailReceiver mr : mailReceiver) {
            MailJob mailJob = mailJobCatalog.create()
                    .receiver(mr)
                    .save();
            mailJobBeans.add((MailJobBean) mailJob);
        }
        this.mailJobs.addAll(mailJobBeans);
        return this;
    }

    @Override
    public MailTask receiver(MailReceiver mailReceiver) {
        MailJob mailJob = mailJobCatalog.create()
                .receiver(mailReceiver)
                .save();
        this.mailJobs.add((MailJobBean) mailJob);
        return this;
    }

    @Override
    public MailTask removeMailJob(MailJob mailJob) {
        this.mailJobs.remove(mailJob);
        return this;
    }

    @Override
    public MailTask removeAllMailJobs() {
        this.mailJobs.clear();
        return this;
    }

    @Override
    public MailTask template(byte[] content) {
        this.content = content;
        this.contentAvailable = Boolean.TRUE;
        return this;
    }

    @Override
    public MailTask save() {
        entityManager.merge(this);
        return this;
    }



    @Override
    public MailTaskSummary asSummary() {
        return new MailTaskSummaryBean(id, comment, topic, contentAvailable, mailJobs.size(), creationDate, status);
    }

    @Override
    public MailTaskDetailed asDetailed() {
        return new MailTaskDetailedBean(id, comment, topic, senderName, senderAddress, contentAvailable,creationDate, status, mailJobs());
    }
}
