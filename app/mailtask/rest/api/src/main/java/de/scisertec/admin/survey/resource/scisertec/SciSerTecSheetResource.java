package de.scisertec.admin.survey.resource.scisertec;

import de.scisertec.admin.survey.model.PersonCreateRequest;
import de.scisertec.admin.survey.model.SheetFillRequest;
import de.scisertec.admin.survey.model.view.SheetContent;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.spi.http.HttpContext;

@Path("survey/scisertec/{surveyId}/sheet")
public interface SciSerTecSheetResource {

    @GET
    @Path("{sheetUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    SheetContent getContent(@PathParam("sheetUuid") String sheetUuid);

    @PUT
    @Path("{sheetUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    SheetContent fill(@PathParam("sheetUuid") String sheetUuid, SheetFillRequest request, @Context HttpServletRequest req);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    SheetContent create(@PathParam("surveyId") Long surveyId, @Context HttpServletRequest req);

    @POST
    @Path("{sheetUuid}/submit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    SheetContent submit(@PathParam("surveyId") Long surveyId, @PathParam("sheetUuid") String sheetUuid, PersonCreateRequest request, @Context HttpServletRequest req);

}
