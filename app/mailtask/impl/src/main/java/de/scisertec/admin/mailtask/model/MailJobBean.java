package de.scisertec.admin.mailtask.model;

import de.scisertec.admin.mailtask.model.view.MailJobEntry;
import de.scisertec.admin.mailtask.model.view.MailJobEntryBean;
import de.scisertec.admin.mailtask.qualifier.MailTaskPersistence;

import javax.inject.Inject;
import javax.persistence.*;

@Entity
@Table(name = "mailjob")
public class MailJobBean implements MailJob {

    @Id
    @GeneratedValue
    Long id;

    @OneToOne(cascade = CascadeType.ALL)
    MailReceiverBean mailReceiverBean;

    MailJobStatus status = MailJobStatus.PENDING;

    @Transient
    @Inject
    @MailTaskPersistence
    EntityManager entityManager;


    @Override
    public Long id() {
        return id;
    }

    @Override
    public MailReceiver receiver() {
        return mailReceiverBean;
    }

    @Override
    public MailJobStatus status() {
        return status;
    }

    @Override
    public MailJob status(MailJobStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public MailJob receiver(MailReceiver mailReceiver) {
        this.mailReceiverBean = (MailReceiverBean) mailReceiver;
        return this;
    }

    @Override
    public MailJob save() {
        entityManager.merge(this);
        return this;
    }


    @Override
    public MailJobEntry asEntry() {
        return new MailJobEntryBean(id, mailReceiverBean.emailAddress, mailReceiverBean.title,
                mailReceiverBean.firstName, mailReceiverBean.lastName, status);
    }
}
