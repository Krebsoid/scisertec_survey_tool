package de.scisertec.admin.core.interceptor;

import de.scisertec.admin.core.qualifier.RunLocal;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@RunLocal
public class RunLocalInterceptor {

    @Inject
    VirtualConversation virtualConversation;

    @AroundInvoke
    public Object aroundInvoke(InvocationContext invocationContext) throws Exception {
        Object proceed;
        virtualConversation.begin();
        proceed = invocationContext.proceed();
        virtualConversation.end();
        return proceed;
    }

}
