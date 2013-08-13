package de.scisertec.admin.mailtask.service.jpa;


import de.scisertec.admin.core.service.jpa.QueryBuilderBean;
import de.scisertec.admin.mailtask.qualifier.MailTaskPersistence;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Dependent
public class MailTaskQueryBuilder extends QueryBuilderBean {

    @Inject
    @MailTaskPersistence
    EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
