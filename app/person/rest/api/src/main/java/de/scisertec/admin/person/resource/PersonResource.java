package de.scisertec.admin.person.resource;


import de.scisertec.admin.person.model.view.PersonContent;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("person")
public interface PersonResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<PersonContent> readAll();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    PersonContent read(@PathParam("id") Long personId);

    @GET
    @Path("group/{groupIdentifier}")
    @Produces(MediaType.APPLICATION_JSON)
    List<PersonContent> readByGroup(@PathParam("groupIdentifier") String groupIdentifier);

}
