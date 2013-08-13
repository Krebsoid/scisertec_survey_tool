package de.scisertec.admin.survey.resource.scisertec;

import de.scisertec.admin.account.model.Group;
import de.scisertec.admin.account.model.Role;
import de.scisertec.admin.account.service.GroupCatalog;
import de.scisertec.admin.account.service.RoleCatalog;
import de.scisertec.admin.mailtask.service.MailTaskCatalog;
import de.scisertec.admin.survey.model.ConcreteSurveyType;
import de.scisertec.admin.survey.model.Survey;
import de.scisertec.admin.survey.model.SurveyStatus;
import de.scisertec.admin.survey.model.SurveyUpdateRequest;
import de.scisertec.admin.survey.model.scisertec.SciSerTecSurvey;
import de.scisertec.admin.survey.model.view.SurveyDetailed;
import de.scisertec.admin.survey.model.view.SurveyResults;
import de.scisertec.admin.survey.model.view.SurveySummary;
import de.scisertec.admin.survey.service.SurveyCatalog;

import javax.inject.Inject;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;

public class SciSerTecSurveyResourceBean implements SciSerTecSurveyResource {

    @Inject
    SurveyCatalog surveyCatalog;

    @Inject
    MailTaskCatalog mailTaskCatalog;

    @Inject
    GroupCatalog groupCatalog;

    @Inject
    RoleCatalog roleCatalog;

    @Override
    public List<SurveySummary> readAll() {
        List<SurveySummary> surveys = new ArrayList<SurveySummary>();
        for(Survey survey : surveyCatalog.findAll()) {
            if(survey instanceof SciSerTecSurvey) {
                surveys.add(survey.asSummary());
            }
        }
        return surveys;
    }

    @Override
    public SurveyResults getResults(@PathParam("id") Long surveyId) {
        Survey survey = surveyCatalog.findById(surveyId);
        return survey.asResults();
    }

    @Override
    public SurveyDetailed read(@PathParam("id") Long surveyId) {
        SciSerTecSurvey survey = (SciSerTecSurvey) surveyCatalog.findById(surveyId);
        return survey.asDetailed();
    }

    @Override
    public String csv(@PathParam("id") Long surveyId) {
        SciSerTecSurvey survey = (SciSerTecSurvey) surveyCatalog.findById(surveyId);
        return survey.asCsv();
    }

    @Override
    public SurveyDetailed create() {
        SciSerTecSurvey survey = (SciSerTecSurvey) surveyCatalog.create(ConcreteSurveyType.SCISERTEC_CUSTOMER);
        return survey.asDetailed();
    }

    @Override
    public SurveyDetailed save(@PathParam("id") Long surveyId, SurveyUpdateRequest request) {
        SciSerTecSurvey survey = (SciSerTecSurvey) surveyCatalog.findById(surveyId);
        survey.name(request.getName()).expirationDate(request.getExpirationDate()).save();
        return survey.asDetailed();
    }

    @Override
    public SurveyDetailed start(@PathParam("id") Long surveyId) {
        SciSerTecSurvey survey = (SciSerTecSurvey) surveyCatalog.findById(surveyId);
        Role role = roleCatalog.create().name("participant");
        Group group = groupCatalog.create()
                .name(survey.name())
                .identifier("survey_scisertec_"+survey.id())
                .addRole(role)
                .defaultRole(role)
                .save();
        survey.group(group).status(SurveyStatus.RUNNING).save();
        return survey.asDetailed();
    }

    @Override
    public void delete(@PathParam("id") Long surveyId) {
        surveyCatalog.delete(surveyId);
    }
}
