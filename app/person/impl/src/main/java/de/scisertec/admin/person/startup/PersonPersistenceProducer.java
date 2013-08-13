package de.scisertec.admin.person.startup;

import de.scisertec.admin.person.qualifier.PersonPersistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class PersonPersistenceProducer {

    @Produces
    @PersonPersistence
    @PersistenceContext(unitName = "scisertec_person")
    EntityManager entityManager;

}
