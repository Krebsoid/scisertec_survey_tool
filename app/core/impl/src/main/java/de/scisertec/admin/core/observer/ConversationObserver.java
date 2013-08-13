package de.scisertec.admin.core.observer;


import org.apache.commons.lang.StringUtils;
import org.jboss.solder.servlet.event.Destroyed;
import org.jboss.solder.servlet.event.Initialized;
import org.jboss.solder.servlet.event.Path;
import org.jboss.weld.context.http.Http;
import org.jboss.weld.context.http.HttpConversationContext;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConversationObserver {

    @Inject
    @Http
    HttpConversationContext context;

    public void onRequestInitialise(@Observes @Initialized @Path("api") HttpServletRequest servletRequest) {
        String cid = servletRequest.getHeader("cid");
        if (cid != null) {
            if (StringUtils.isNotEmpty(cid)) {
                context.activate(cid);
            }
        }
        else {
            context.activate();
        }
    }

    public void onResponseDestroyed(@Observes @Destroyed @Path("api") HttpServletResponse servletResponse) {
        context.invalidate();
        context.deactivate();
    }


}
