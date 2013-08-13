package de.scisertec.admin.account.service;

import de.scisertec.admin.account.model.*;
import de.scisertec.admin.account.qualifier.AccountPersistence;
import de.scisertec.admin.account.service.jpa.AccountQueryBuilder;
import org.jboss.seam.transaction.Transactional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.EntityManager;


@ApplicationScoped
public class GroupCatalogBean implements GroupCatalog {

    @Inject
    @AccountPersistence
    EntityManager entityManager;

    @Inject
    Instance<AccountQueryBuilder> queryBuilder;

    @Override
    @Transactional
    public Group create() {
        GroupBean group = new GroupBean();
        entityManager.persist(group);
        entityManager.flush();
        return group;
    }

    @Override
    public Group findById(Long id) {
        return queryBuilder.get()
                .select(GroupBean.class)
                .where()
                .equal(GroupBean_.id, id)
                .find();
    }

    @Override
    public Group findByIdentifier(String identifier) {
        return queryBuilder.get()
                .select(GroupBean.class)
                .where()
                .equal(GroupBean_.identifier, identifier)
                .find();
    }
}
