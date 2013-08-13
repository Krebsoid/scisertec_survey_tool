package de.scisertec.admin.account.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserStateExceptionMapper implements ExceptionMapper<UserStateException>
{
    public Response toResponse(UserStateException exception) {
        return Response.serverError().entity("Not logged in").build();
    }
}