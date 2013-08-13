package de.scisertec.admin.account.startup;

import de.scisertec.admin.account.qualifier.AccountPersistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class AccountPersistenceProducer {

    @Produces
    @AccountPersistence
    @PersistenceContext(unitName = "scisertec_account")
    EntityManager entityManager;

}
