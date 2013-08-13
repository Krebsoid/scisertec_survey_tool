package de.scisertec.admin.survey.model.view;


import de.scisertec.admin.survey.model.SurveyType;
import org.joda.time.DateTime;

public interface SurveyUpdate {

    Long getMailTaskId();
    String getName();
    SurveyType getSurveyType();
    DateTime getExpirationDate();

}
