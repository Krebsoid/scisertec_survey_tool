package de.scisertec.admin.survey.resource.vcongress;

import de.scisertec.admin.survey.model.SheetFillRequest;
import de.scisertec.admin.survey.model.view.SheetContent;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("survey/vcongress/{surveyId}/sheet")
public interface vCongressSheetResource {

    @GET
    @Path("{sheetUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    SheetContent getContent(@PathParam("sheetUuid") String sheetUuid);

    @PUT
    @Path("{sheetUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    SheetContent fill(@PathParam("sheetUuid") String sheetUuid, SheetFillRequest request);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    SheetContent create(@PathParam("surveyId") Long surveyId);

    @POST
    @Path("{sheetUuid}/submit")
    @Produces(MediaType.APPLICATION_JSON)
    SheetContent submit(@PathParam("sheetUuid") String sheetUuid);
}
