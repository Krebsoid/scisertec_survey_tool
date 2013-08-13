package de.scisertec.admin.account.startup;

import de.scisertec.admin.account.startup.data.UserImport;
import de.scisertec.admin.core.startup.ApplicationStarted;
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
    UserImport userImport;
    Boolean userToBeImported = Boolean.TRUE;

    @Inject
    Event<UserImported> userImportedEvent;

    public void onStartup(@Observes ApplicationStarted applicationStarted) {
        if(userToBeImported) {
            logger.info("User Import Start");
            userImport.initialize();
            logger.info("User Import End");
            UserImported userImported = new UserImported(userImport.getUser(), userImport.getAdmin());
            userImportedEvent.fire(userImported);
        }
        else {
            UserImported userImported = new UserImported();
            userImportedEvent.fire(userImported);
        }
    }

}
