package de.scisertec.admin.core.interceptor;

import org.jboss.weld.context.bound.BoundConversationContext;
import org.jboss.weld.context.bound.BoundRequestContext;
import org.jboss.weld.context.bound.BoundSessionContext;
import org.jboss.weld.context.bound.MutableBoundRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;

@ApplicationScoped
public class VirtualConversation {

    @Inject
    private BoundConversationContext boundConversationContext;
    @Inject
    private BoundRequestContext boundRequestContext;
    @Inject
    private BoundSessionContext boundSessionContext;

    public void begin() {
        HashMap<String, Object> requestMap = new HashMap<String, Object>();
        HashMap<String, Object> sessionMap = new HashMap<String, Object>();
        boundSessionContext.associate(sessionMap);
        boundSessionContext.activate();
        boundRequestContext.associate(requestMap);
        boundRequestContext.activate();
        boundConversationContext.associate(new MutableBoundRequest(requestMap, sessionMap));
        boundConversationContext.activate();
    }

    public void end() {
        boundConversationContext.invalidate();
        boundConversationContext.deactivate();
        boundRequestContext.invalidate();
        boundRequestContext.deactivate();
        boundSessionContext.invalidate();
        boundSessionContext.deactivate();
    }



}
