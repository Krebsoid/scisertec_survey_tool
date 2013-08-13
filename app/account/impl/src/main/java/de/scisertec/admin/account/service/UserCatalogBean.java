package de.scisertec.admin.account.service;

import de.scisertec.admin.account.model.*;
import de.scisertec.admin.account.model.view.Login;
import de.scisertec.admin.account.qualifier.AccountPersistence;
import de.scisertec.admin.account.service.jpa.AccountQueryBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;


@ApplicationScoped
public class UserCatalogBean implements UserCatalog {

    @Inject
    @AccountPersistence
    EntityManager entityManager;

    @Inject
    Instance<AccountQueryBuilder> queryBuilder;

    @Override
    public User create() {
        CredentialBean credential = new CredentialBean();
        entityManager.persist(credential);
        UserBean user = new UserBean();
        user.credential(credential);
        entityManager.persist(user);
        entityManager.flush();
        return user;
    }

    @Override
    public User findById(Long id) {
        return queryBuilder.get().select(UserBean.class)
                .where()
                .equal(UserBean_.id, id)
                .find();
    }

    @Override
    public User findByLogin(Login login) {
        return queryBuilder.get().select(UserBean.class)
                .join(UserBean_.credential)
                .where()
                .equal(CredentialBean_.userName, login.getUserName())
                .find();
    }

    @Override
    public List<User> findByGroup(Group group) {
        return queryBuilder.get().select(UserBean.class)
                .join(UserBean_.relationships)
                .equal(RelationshipBean_.group, group).findAll();
    }

    @Override
    public User findByMailAddress(String mailAddress) {
        return queryBuilder.get().select(UserBean.class)
                .join(UserBean_.credential)
                .where()
                .equal(CredentialBean_.userName, mailAddress)
                .find();
    }

}
