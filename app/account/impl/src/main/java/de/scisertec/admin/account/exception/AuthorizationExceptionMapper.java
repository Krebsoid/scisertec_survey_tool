package de.scisertec.admin.account.exception;

import org.jboss.seam.security.AuthorizationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthorizationExceptionMapper implements ExceptionMapper<AuthorizationException>
{
    public Response toResponse(AuthorizationException exception) {
        return Response.status(Response.Status.UNAUTHORIZED).entity("You are not allowed to proceed!").build();
    }
}