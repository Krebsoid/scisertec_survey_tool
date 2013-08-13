package de.scisertec.admin.core.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoResourceFoundExceptionMapper implements ExceptionMapper<NoResourceFoundException>
{

    public Response toResponse(NoResourceFoundException exception) {
        String message = "Resource from type: " + exception.getClazz().getName() + " with Id: " + exception.getId() + " not existent";
        return Response.status(Response.Status.GONE).entity(message).build();
    }

}