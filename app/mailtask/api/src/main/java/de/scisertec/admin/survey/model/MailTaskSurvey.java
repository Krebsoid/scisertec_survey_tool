package de.scisertec.admin.survey.model;

import de.scisertec.admin.mailtask.model.MailTask;

public interface MailTaskSurvey extends Survey {

    MailTask mailTask();

    MailTaskSurvey mailTask(MailTask mailTask);

}
