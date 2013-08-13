package de.scisertec.admin.survey.model;

import de.scisertec.admin.mailtask.model.MailTask;
import de.scisertec.admin.mailtask.service.MailTaskCatalog;
import de.scisertec.admin.survey.model.view.MailTaskSurveyDetailedBean;
import de.scisertec.admin.survey.model.view.SurveyDetailed;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public abstract class MailTaskSurveyBean extends SurveyBean implements MailTaskSurvey {

    public Long mailTaskId;

    @Transient
    @Inject
    protected MailTaskCatalog mailTaskCatalog;


    @Override
    public MailTask mailTask() {
        return mailTaskCatalog.findById(mailTaskId);
    }

    @Override
    public MailTaskSurvey mailTask(MailTask mailTask) {
        this.mailTaskId = mailTask.id();
        return self();
    }

    @Override
    public SurveyDetailed asDetailed() {
        return new MailTaskSurveyDetailedBean(id, status, surveyType, linkAffix(), concreteSurveyType, name, creationDate,
                expirationDate, sheets, mailTask());
    }

    public MailTaskSurvey self() {
        return this;
    }
}
