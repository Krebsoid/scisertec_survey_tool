package de.scisertec.admin.account.resource;

import de.scisertec.admin.account.model.LoginRequest;
import de.scisertec.admin.account.model.view.UserState;
import de.scisertec.admin.account.security.LoggedIn;
import de.scisertec.admin.account.security.LoggedOut;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("authorization")
public interface AuthorizationResource {

    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @LoggedOut
    UserState login(LoginRequest loginRequest);

    @Path("logout")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @LoggedIn
    Boolean logout();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    UserState state();

}
