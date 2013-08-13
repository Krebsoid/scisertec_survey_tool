package de.scisertec.admin.survey.service;

import de.scisertec.admin.survey.model.MailTaskSurvey;

public interface SurveyDelivery {

    void start(MailTaskSurvey survey);

}
