package de.scisertec.admin.survey.service;

import de.scisertec.admin.mailtask.model.view.MailReceiverContent;
import de.scisertec.admin.mailtask.model.view.MailReceiverContentContainer;
import de.scisertec.admin.mailtask.qualifier.LiveMail;
import de.scisertec.admin.mailtask.qualifier.TestMail;
import de.scisertec.admin.mailtask.service.MailTaskDelivery;
import de.scisertec.admin.survey.model.*;
import de.scisertec.admin.survey.model.view.SurveyReceiverContent;
import de.scisertec.admin.survey.model.view.SurveyReceiverContentBean;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class SurveyDeliveryBean implements SurveyDelivery {

    @Inject
    MailTaskDelivery mailTaskDelivery;

    @Inject
    SurveyCatalog surveyCatalog;

    Set<Sheet> sheets = new HashSet<Sheet>();

    public void onSurveyTestMail(@Observes @TestMail MailReceiverContentContainer container) {
        Survey survey = surveyCatalog.findByMailTask(container.getMailTaskId());
        if(survey != null && survey instanceof PersonalSurvey) {
            MailReceiverContent mailReceiver = container.getReceiverContent();
            String link = " <a href=\"http://info.scisertec.de/survey/#/vcongress/1/Welcome\">Link</a> ";
            SurveyReceiverContent surveyReceiverContentBean = new SurveyReceiverContentBean(mailReceiver.getId(), link, mailReceiver.getSalutation(),
                    mailReceiver.getTitle(), mailReceiver.getFirstName(), mailReceiver.getLastName());
            container.setReceiverContent(surveyReceiverContentBean);
        }
    }

    public void onSurveyLiveMail(@Observes @LiveMail MailReceiverContentContainer container) {
        Survey survey = surveyCatalog.findByMailTask(container.getMailTaskId());
        if(survey != null && survey instanceof PersonalSurvey) {
            MailReceiverContent mailReceiver = container.getReceiverContent();

            String uuid = findUuid(mailReceiver.getId());
            String url = survey.linkAffix() + survey.id() + "/" + uuid;

            String link = " <a href=\"" + url + "\">Link</a> ";
            SurveyReceiverContent surveyReceiverContentBean = new SurveyReceiverContentBean(mailReceiver.getId(), link,
                    mailReceiver.getSalutation(), mailReceiver.getTitle(), mailReceiver.getFirstName(), mailReceiver.getLastName());
            container.setReceiverContent(surveyReceiverContentBean);
        }
    }

    @Override
    public void start(MailTaskSurvey survey) {
        if(survey instanceof PersonalSurvey) {
            sheets = survey.sheets();
        }
        mailTaskDelivery.start(survey.mailTask());
    }

    private String findUuid(Long mailReceiverId) {
        for(Sheet sheet : sheets) {
            PersonalSheet personalSheet = (PersonalSheet) sheet;
            if(mailReceiverId.equals(personalSheet.mailReceiver().id())) {
                return personalSheet.uuid();
            }
        }
        return null;
    }
}
