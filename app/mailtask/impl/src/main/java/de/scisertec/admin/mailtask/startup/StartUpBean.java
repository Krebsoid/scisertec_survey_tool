package de.scisertec.admin.mailtask.startup;

import de.scisertec.admin.mailtask.startup.data.MailTaskImport;
import de.scisertec.admin.person.startup.PersonImported;
import org.jboss.solder.logging.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class StartUpBean {

    @Inject
    Logger logger;

    @Inject
    MailTaskImport mailTaskImport;
    Boolean mailTaskToBeImported = Boolean.TRUE;

    public void onStartup(@Observes PersonImported personImported) {
        if(mailTaskToBeImported) {
            logger.info("MailTask Import Start");
            mailTaskImport.initialize();
            logger.info("MailTask Import End");
        }
    }

}
