package de.scisertec.admin.mailtask.resource;

import de.scisertec.admin.mailtask.model.MailTaskTestRequest;
import de.scisertec.admin.mailtask.model.MailTaskUpdateRequest;
import de.scisertec.admin.mailtask.model.MailTemplateImportRequest;
import de.scisertec.admin.mailtask.model.view.MailTaskSummary;
import de.scisertec.admin.mailtask.model.view.MailTaskDetailed;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.spi.validation.ValidateRequest;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("mailtask")
public interface MailTaskResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<MailTaskSummary> readAll();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    MailTaskDetailed read(@PathParam("id") Long mailTaskId);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    MailTaskDetailed create();

    @POST
    @Path("{id}/template")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @ValidateRequest
    void updateTemplate(@PathParam("id") Long mailTaskId, @Valid @MultipartForm MailTemplateImportRequest request);

    @GET
    @Path("{id}/template")
    @Produces(MediaType.TEXT_HTML)
    String readTemplate(@PathParam("id") Long mailTaskId);

    @POST
    @Path("{id}/send")
    void send(@PathParam("id") Long mailTaskId);

    @POST
    @Path("{id}/template/test")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ValidateRequest
    void testTemplate(@PathParam("id") Long mailTaskId, @Valid MailTaskTestRequest request);

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    MailTaskDetailed update(@PathParam("id") Long mailTaskId, MailTaskUpdateRequest mailTaskUpdateRequestBean);

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void delete(@PathParam("id") Long mailTaskId);

}
