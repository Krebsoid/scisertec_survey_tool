package de.scisertec.admin.survey.service;

import de.scisertec.admin.mailtask.model.view.MailTaskSummary;
import de.scisertec.admin.survey.model.*;

import java.util.List;

public interface SurveyCatalog {

    Survey create(ConcreteSurveyType type);

    List<Survey> findAll();
    Survey findById(Long id);
    MailTaskSurvey findByMailTask(Long mailTaskId);

    List<MailTaskSummary> findSuitableMailTasks(MailTaskSurvey survey);

    void delete(Long id);
}
