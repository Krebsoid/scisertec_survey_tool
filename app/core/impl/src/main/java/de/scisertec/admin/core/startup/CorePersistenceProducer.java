package de.scisertec.admin.core.startup;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

public class CorePersistenceProducer {

    @Produces
    @PersistenceContext(unitName = "scisertec_core")
    EntityManager entityManager;

}
