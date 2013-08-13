package de.scisertec.admin.survey.resource.scisertec;

import de.scisertec.admin.survey.model.SurveyUpdateRequest;
import de.scisertec.admin.survey.model.view.SurveyDetailed;
import de.scisertec.admin.survey.model.view.SurveyResults;
import de.scisertec.admin.survey.model.view.SurveySummary;
import de.scisertec.admin.survey.resource.SurveyResource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("survey/scisertec")
public interface SciSerTecSurveyResource extends SurveyResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<SurveySummary> readAll();

    @GET
    @Path("{id}/results")
    @Produces(MediaType.APPLICATION_JSON)
    SurveyResults getResults(@PathParam("id") Long surveyId);

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    SurveyDetailed read(@PathParam("id") Long surveyId);

    @GET
    @Path("{id}/data.csv")
    @Produces("text/csv")
    String csv(@PathParam("id") Long surveyId);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    SurveyDetailed create();

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    SurveyDetailed save(@PathParam("id") Long surveyId, SurveyUpdateRequest request);

    @POST
    @Path("{id}/start")
    @Produces(MediaType.APPLICATION_JSON)
    SurveyDetailed start(@PathParam("id") Long surveyId);

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void delete(@PathParam("id") Long surveyId);

}
