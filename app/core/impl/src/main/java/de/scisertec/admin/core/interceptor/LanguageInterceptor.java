package de.scisertec.admin.core.interceptor;


import org.apache.commons.lang.LocaleUtils;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.jboss.seam.international.Alter;
import org.jboss.solder.core.Client;
import org.jboss.solder.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import java.util.Locale;

@Provider
@ServerInterceptor
public class LanguageInterceptor implements PreProcessInterceptor {

    @Inject
    @Alter
    @Client
    Event<Locale> localeEvent;

    @Inject
    Logger logger;


    @Override
    public ServerResponse preProcess(HttpRequest request, ResourceMethod method) throws Failure, WebApplicationException {
        try {
            if(request.getHttpHeaders().getCookies().containsKey("language")) {
                Locale locale = LocaleUtils.toLocale(request.getHttpHeaders().getCookies().get("language").getValue());
                localeEvent.fire(locale);
                return null;
            }
        }
        catch(Exception e) {
            logger.warn("No or wrong locale format was given, default language is loaded: "+ request.getHttpHeaders().getCookies().get("language").toString());
            localeEvent.fire(Locale.UK);
            return null;
        }
        return null;
    }
}
