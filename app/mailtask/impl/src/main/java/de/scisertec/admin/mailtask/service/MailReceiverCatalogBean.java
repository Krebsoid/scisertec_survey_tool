package de.scisertec.admin.mailtask.service;

import de.scisertec.admin.core.exception.NoResourceFoundException;
import de.scisertec.admin.mailtask.model.MailReceiver;
import de.scisertec.admin.mailtask.model.MailReceiverBean;
import de.scisertec.admin.mailtask.model.view.MailReceiverCreation;
import de.scisertec.admin.mailtask.qualifier.MailTaskPersistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class MailReceiverCatalogBean implements MailReceiverCatalog {

    @Inject
    @MailTaskPersistence
    EntityManager entityManager;

    public MailReceiver create() {
        MailReceiverBean mailReceiverBean = new MailReceiverBean();
        entityManager.persist(mailReceiverBean);
        entityManager.flush();
        return mailReceiverBean;
    }

    public MailReceiver create(MailReceiverCreation mailReceiverCreation) {
        return create()
                .emailAddress(mailReceiverCreation.getEmailAddress())
                .name(" "+mailReceiverCreation.getLastName(), " "+mailReceiverCreation.getFirstName())
                .title(" "+mailReceiverCreation.getTitle())
                .salutation(mailReceiverCreation.getSalutation())
                .save();
    }

    @Override
    public MailReceiver findById(Long id) {
        MailReceiver result;
        result = entityManager.find(MailReceiverBean.class, id);
        if(result == null) {
            throw new NoResourceFoundException("Resource not found", MailReceiverBean.class, id);
        }
        return result;
    }

}
