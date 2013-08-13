package de.scisertec.admin.survey.model;

import de.scisertec.admin.core.model.TechnicalId;
import de.scisertec.admin.mailtask.model.MailTask;
import de.scisertec.admin.survey.model.view.SurveyDetailed;
import de.scisertec.admin.survey.model.view.SurveyResults;
import de.scisertec.admin.survey.model.view.SurveySummary;
import org.joda.time.DateTime;

import java.util.Set;

public interface Survey extends TechnicalId {

    DateTime creationDate();
    DateTime expirationDate();
    String name();

    String linkAffix();

    SurveyStatus status();

    Set<Sheet> sheets();
    SurveyType surveyType();
    ConcreteSurveyType concreteSurveyType();

    Survey name(String name);
    Survey status(SurveyStatus status);
    Survey surveyType(SurveyType surveyType);
    Survey concreteSurveyType(ConcreteSurveyType concreteSurveyType);
    Survey sheet(Sheet sheet);

    Survey expirationDate(DateTime expirationDate);

    Survey save();

    SurveySummary asSummary();
    SurveyDetailed asDetailed();
    SurveyResults asResults();
    String asCsv();

}
