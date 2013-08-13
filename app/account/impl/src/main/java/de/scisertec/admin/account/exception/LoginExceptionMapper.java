package de.scisertec.admin.account.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class LoginExceptionMapper implements ExceptionMapper<LoginException>
{
    public Response toResponse(LoginException exception) {
        return Response.status(Response.Status.UNAUTHORIZED).entity("Username and/or Password incorrect.").build();
    }
}