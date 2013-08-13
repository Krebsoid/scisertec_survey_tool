package de.scisertec.admin.core.exception;

import org.hibernate.validator.method.MethodConstraintViolation;
import org.hibernate.validator.method.MethodConstraintViolationException;

import javax.validation.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.Iterator;

@Provider
public class RestExceptionMapper implements ExceptionMapper<MethodConstraintViolationException>
{

    public Response toResponse(MethodConstraintViolationException exception) {
        ArrayList<Violation> violations = new ArrayList<Violation>();
        for(MethodConstraintViolation constraintViolation : exception.getConstraintViolations()){
            Violation violation = new Violation();
            String key = "";
            Iterator<Path.Node> iterator = constraintViolation.getPropertyPath().iterator();
            while(iterator.hasNext()) {
                key = iterator.next().getName();
            }
            violation.setKey(key);
            violation.setValue(constraintViolation.getMessage());
            violations.add(violation);
        }

        return Response.serverError().entity(violations).build();
    }

}