package de.scisertec.admin.survey.model.view;

import de.scisertec.admin.mailtask.model.view.MailTaskSummary;

public interface MailTaskSurveyDetailed extends SurveyDetailed {

    MailTaskSummary getMailTask();

}
