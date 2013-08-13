package de.scisertec.admin.account.service;

import de.scisertec.admin.account.model.*;
import de.scisertec.admin.account.qualifier.AccountPersistence;
import de.scisertec.admin.account.service.jpa.AccountQueryBuilder;
import org.jboss.seam.transaction.Transactional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Set;


@ApplicationScoped
public class RelationshipCatalogBean implements RelationshipCatalog {

    @Inject
    @AccountPersistence
    EntityManager entityManager;

    @Inject
    Instance<AccountQueryBuilder> queryBuilder;

    @Override
    @Transactional
    public Relationship create(Group group) {
        RelationshipBean relationship = new RelationshipBean();
        relationship.group(group).addRole(relationship.group().defaultRole());
        entityManager.persist(relationship);
        entityManager.flush();
        return relationship;
    }

    @Override
    @Transactional
    public Relationship create(Group group, Set<Role> roles) {
        RelationshipBean relationship = new RelationshipBean();
        relationship.group(group).addRoles(roles);
        entityManager.persist(relationship);
        entityManager.flush();
        return relationship;
    }

    @Override
    public Relationship findById(Long id) {
        return queryBuilder.get()
                .select(RelationshipBean.class)
                .where()
                .equal(RelationshipBean_.id, id)
                .find();
    }
}
