package de.scisertec.admin.person.startup;

import de.scisertec.admin.account.startup.UserImported;
import de.scisertec.admin.person.startup.data.PersonImport;
import org.jboss.solder.logging.Logger;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class StartUpBean {

    @Inject
    Logger logger;

    @Inject
    PersonImport personImport;
    Boolean personToBeImported = Boolean.TRUE;

    @Inject
    Event<PersonImported> personImportedEvent;

    public void onStartup(@Observes UserImported userImported) {
        if(personToBeImported) {
            logger.info("Person Import Start");
            personImport.initialize(userImported.user, userImported.admin);
            logger.info("Person Import End");
            PersonImported personImported = new PersonImported(personImport.getUser(), personImport.getAdmin());
            personImportedEvent.fire(personImported);
        }
        else {
            PersonImported personImported = new PersonImported();
            personImportedEvent.fire(personImported);
        }
    }

}
