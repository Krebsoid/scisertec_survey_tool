package de.scisertec.admin.mailtask.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidTemplateExceptionMapper implements ExceptionMapper<InvalidTemplateException>
{
    public Response toResponse(InvalidTemplateException exception) {
        return Response.ok("<span style='color: red; font-weight: bold'>Invalid Template - Maybe no survey is associated yet</span>").build();
    }
}