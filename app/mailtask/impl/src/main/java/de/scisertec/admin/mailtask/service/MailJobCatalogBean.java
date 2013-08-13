package de.scisertec.admin.mailtask.service;

import de.scisertec.admin.core.exception.NoResourceFoundException;
import de.scisertec.admin.mailtask.model.MailJob;
import de.scisertec.admin.mailtask.model.MailJobBean;
import de.scisertec.admin.mailtask.model.MailReceiver;
import de.scisertec.admin.mailtask.model.MailReceiverBean;
import de.scisertec.admin.mailtask.qualifier.MailTaskPersistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class MailJobCatalogBean implements MailJobCatalog {

    @Inject
    @MailTaskPersistence
    EntityManager entityManager;

    @Override
    public MailJob create() {
        MailJobBean mailJobBean = new MailJobBean();
        entityManager.persist(mailJobBean);
        entityManager.flush();
        return mailJobBean;
    }

    @Override
    public MailJob findById(Long id) {
        MailJob result;
        result = entityManager.find(MailJobBean.class, id);
        if(result == null) {
            throw new NoResourceFoundException("Resource not found", MailJob.class, id);
        }
        return result;
    }

}
