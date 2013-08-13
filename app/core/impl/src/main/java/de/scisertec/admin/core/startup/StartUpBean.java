package de.scisertec.admin.core.startup;

import de.scisertec.admin.core.qualifier.RunLocal;
import org.jboss.solder.logging.Logger;
import org.jboss.solder.servlet.WebApplication;
import org.jboss.solder.servlet.event.Started;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class StartUpBean {

    @Inject
    Logger logger;

    @Inject
    Event<ApplicationStarted> applicationStartedEvent;

    @RunLocal
    public void onWebApplicationStarted(@Observes @Started WebApplication application) {

        logger.info("Web App 2 started");
        logger.info("Import Start");
        ApplicationStarted applicationStarted = new ApplicationStarted();
        applicationStartedEvent.fire(applicationStarted);
        logger.info("Import End");

    }

}
