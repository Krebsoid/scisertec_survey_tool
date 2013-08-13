package de.scisertec.admin.survey.model.view;

import de.scisertec.admin.mailtask.model.MailTask;
import de.scisertec.admin.mailtask.model.view.MailTaskSummary;
import de.scisertec.admin.survey.model.ConcreteSurveyType;
import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SurveyStatus;
import de.scisertec.admin.survey.model.SurveyType;
import org.joda.time.DateTime;

import java.util.Set;

public class MailTaskSurveyDetailedBean extends SurveyDetailedBean implements MailTaskSurveyDetailed {

    MailTaskSummary mailTask;

    public MailTaskSurveyDetailedBean(Long id, SurveyStatus status, SurveyType surveyType, String link, ConcreteSurveyType concreteSurveyType,
                                      String name, DateTime creationDate, DateTime expirationDate, Set<Sheet> sheets, MailTask mailTask) {
        super(id, status, surveyType, link, concreteSurveyType, name, creationDate, expirationDate, sheets);
        if(mailTask != null) {
            this.mailTask = mailTask.asSummary();
        }
    }

    @Override
    public MailTaskSummary getMailTask() {
        return mailTask;
    }
}
