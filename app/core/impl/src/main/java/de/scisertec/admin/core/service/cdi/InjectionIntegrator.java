package de.scisertec.admin.core.service.cdi;

import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.metamodel.source.MetadataImplementor;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;
import org.jboss.solder.logging.Logger;

/**
 * @author : Patrick Bittner
 */
public class InjectionIntegrator implements Integrator {

    private final static Logger log = Logger.getLogger(InjectionIntegrator.class);

    CdiInjectionEventListener injectionEventListener = new CdiInjectionEventListener();

    @Override public void integrate(Configuration configuration,
                                    SessionFactoryImplementor sessionFactory,
                                    SessionFactoryServiceRegistry serviceRegistry) {

        EventListenerRegistry listenerRegistry = serviceRegistry.getService(EventListenerRegistry.class);

        listenerRegistry
                .getEventListenerGroup(EventType.POST_LOAD)
                .appendListener(injectionEventListener);
        listenerRegistry
                .getEventListenerGroup(EventType.POST_COMMIT_INSERT)
                .appendListener(injectionEventListener);
        listenerRegistry
                .getEventListenerGroup(EventType.POST_INSERT)
                .appendListener(injectionEventListener);


        log.infof("Enabling CDI Injection into JPA Entities :%s", configuration.getProperty("hibernate.session_factory_name"));

    }

    @Override public void integrate(MetadataImplementor metadata,
                                    SessionFactoryImplementor sessionFactory,
                                    SessionFactoryServiceRegistry serviceRegistry) {
        // No OP
    }

    @Override public void disintegrate(SessionFactoryImplementor sessionFactory,
                                       SessionFactoryServiceRegistry serviceRegistry) {
        // No OP
    }
}