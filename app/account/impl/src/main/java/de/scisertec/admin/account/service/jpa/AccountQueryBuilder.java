package de.scisertec.admin.account.service.jpa;


import de.scisertec.admin.account.qualifier.AccountPersistence;
import de.scisertec.admin.core.service.jpa.QueryBuilderBean;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Dependent
public class AccountQueryBuilder extends QueryBuilderBean {

    @Inject
    @AccountPersistence
    EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
