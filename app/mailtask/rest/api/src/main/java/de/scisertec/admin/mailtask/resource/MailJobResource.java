package de.scisertec.admin.mailtask.resource;

import de.scisertec.admin.mailtask.model.MailReceiverCreationRequest;
import de.scisertec.admin.mailtask.model.MailReceiverImportRequest;
import de.scisertec.admin.mailtask.model.view.MailJobEntry;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.spi.validation.ValidateRequest;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("mailtask/{id}/mailjob")
public interface MailJobResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<MailJobEntry> getMailJobs(@PathParam("id") Long mailTaskId);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @ValidateRequest
    void addReceiverList(@PathParam("id") Long mailTaskId, @Valid @MultipartForm MailReceiverImportRequest request);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ValidateRequest
    void addReceiver(@PathParam("id") Long mailTaskId, @Valid MailReceiverCreationRequest request);

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    void deleteAll(@PathParam("id") Long mailTaskId);

    @DELETE
    @Path("{mailJobId}")
    @Produces(MediaType.APPLICATION_JSON)
    void delete(@PathParam("id") Long mailTaskId, @PathParam("mailJobId") Long mailJobId);

}
