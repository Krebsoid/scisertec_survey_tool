package de.scisertec.admin.survey.resource.vcongress;

import de.scisertec.admin.mailtask.service.MailTaskCatalog;
import de.scisertec.admin.survey.model.ConcreteSurveyType;
import de.scisertec.admin.survey.model.Survey;
import de.scisertec.admin.survey.model.SurveyStatus;
import de.scisertec.admin.survey.model.SurveyUpdateRequest;
import de.scisertec.admin.survey.model.vcongress.vCongressSurvey;
import de.scisertec.admin.survey.model.vcongress.vCongressSurveyUpdateRequest;
import de.scisertec.admin.survey.model.vcongress.view.vCongressSurveyDetailed;
import de.scisertec.admin.survey.model.view.SurveyDetailed;
import de.scisertec.admin.survey.model.view.SurveyResults;
import de.scisertec.admin.survey.model.view.SurveySummary;
import de.scisertec.admin.survey.service.SurveyCatalog;

import javax.inject.Inject;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;

public class vCongressSurveyResourceBean implements vCongressSurveyResource {

    @Inject
    SurveyCatalog surveyCatalog;

    @Inject
    MailTaskCatalog mailTaskCatalog;

    @Override
    public List<SurveySummary> readAll() {
        List<SurveySummary> surveys = new ArrayList<SurveySummary>();
        for(Survey survey : surveyCatalog.findAll()) {
            if(survey instanceof vCongressSurvey) {
                surveys.add(survey.asSummary());
            }
        }
        return surveys;
    }

    @Override
    public vCongressSurveyDetailed read(@PathParam("id") Long surveyId) {
        vCongressSurvey survey = (vCongressSurvey) surveyCatalog.findById(surveyId);
        return survey.asDetailed();
    }

    @Override
    public SurveyResults getResults(@PathParam("id") Long surveyId) {
        Survey survey = surveyCatalog.findById(surveyId);
        return survey.asResults();
    }

    @Override
    public String csv(@PathParam("id") Long surveyId) {
        vCongressSurvey survey = (vCongressSurvey) surveyCatalog.findById(surveyId);
        return survey.asCsv();
    }

    @Override
    public SurveyDetailed create() {
        vCongressSurvey survey = (vCongressSurvey) surveyCatalog.create(ConcreteSurveyType.VCONGRESS_AFTER_CONGRESS);
        return survey.asDetailed();
    }

    @Override
    public SurveyDetailed save(@PathParam("id") Long surveyId, vCongressSurveyUpdateRequest request) {
        vCongressSurvey survey = (vCongressSurvey) surveyCatalog.findById(surveyId);
        survey.payment(request.getPayment())
                .abstractSubmission(request.getAbstractSubmission())
                .reviewing(request.getReviewing())
                .name(request.getName())
                .expirationDate(request.getExpirationDate())
                .save();
        return survey.asDetailed();
    }

    @Override
    public SurveyDetailed start(@PathParam("id") Long surveyId) {
        vCongressSurvey survey = (vCongressSurvey) surveyCatalog.findById(surveyId);
        survey.status(SurveyStatus.RUNNING).save();
        return survey.asDetailed();
    }

    @Override
    public void delete(@PathParam("id") Long surveyId) {
        surveyCatalog.delete(surveyId);
    }
}
