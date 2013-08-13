package de.scisertec.admin.account.service;

import de.scisertec.admin.account.model.Role;
import de.scisertec.admin.account.model.RoleBean;
import de.scisertec.admin.account.model.RoleBean_;
import de.scisertec.admin.account.qualifier.AccountPersistence;
import de.scisertec.admin.account.service.jpa.AccountQueryBuilder;
import org.jboss.seam.transaction.Transactional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.EntityManager;


@ApplicationScoped
public class RoleCatalogBean implements RoleCatalog {

    @Inject
    @AccountPersistence
    EntityManager entityManager;

    @Inject
    Instance<AccountQueryBuilder> queryBuilder;

    @Override
    @Transactional
    public Role create() {
        RoleBean role = new RoleBean();
        entityManager.persist(role);
        entityManager.flush();
        return role;
    }

    @Override
    public Role findById(Long id) {
        return queryBuilder.get()
                .select(RoleBean.class)
                .where()
                .equal(RoleBean_.id, id)
                .find();
    }
}
