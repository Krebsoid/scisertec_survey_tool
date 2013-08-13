package de.scisertec.admin.survey.resource.vcongress;

import de.scisertec.admin.survey.model.SurveyUpdateRequest;
import de.scisertec.admin.survey.model.vcongress.vCongressSurveyUpdateRequest;
import de.scisertec.admin.survey.model.vcongress.view.vCongressSurveyDetailed;
import de.scisertec.admin.survey.model.view.SurveyDetailed;
import de.scisertec.admin.survey.model.view.SurveyResults;
import de.scisertec.admin.survey.model.view.SurveySummary;
import de.scisertec.admin.survey.resource.SurveyResource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("survey/vcongress")
public interface vCongressSurveyResource extends SurveyResource {

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
    vCongressSurveyDetailed read(@PathParam("id") Long surveyId);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    SurveyDetailed create();

    @GET
    @Path("{id}/data.csv")
    @Produces("text/csv")
    String csv(@PathParam("id") Long surveyId);

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    SurveyDetailed save(@PathParam("id") Long surveyId, vCongressSurveyUpdateRequest request);

    @POST
    @Path("{id}/start")
    @Produces(MediaType.APPLICATION_JSON)
    SurveyDetailed start(@PathParam("id") Long surveyId);

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void delete(@PathParam("id") Long surveyId);

}
