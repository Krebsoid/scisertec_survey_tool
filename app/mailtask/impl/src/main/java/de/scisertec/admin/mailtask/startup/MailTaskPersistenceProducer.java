package de.scisertec.admin.mailtask.startup;

import de.scisertec.admin.mailtask.qualifier.MailTaskPersistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class MailTaskPersistenceProducer {

    @Produces
    @MailTaskPersistence
    @PersistenceContext(unitName = "scisertec_mailtask")
    EntityManager entityManager;

}
