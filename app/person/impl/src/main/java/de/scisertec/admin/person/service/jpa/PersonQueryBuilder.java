package de.scisertec.admin.person.service.jpa;


import de.scisertec.admin.core.service.jpa.QueryBuilderBean;
import de.scisertec.admin.person.qualifier.PersonPersistence;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Dependent
public class PersonQueryBuilder extends QueryBuilderBean {

    @Inject
    @PersonPersistence
    EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
