package de.scisertec.admin.survey.model.vcongress.view;

import de.scisertec.admin.survey.model.ConcreteSurveyType;
import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SurveyStatus;
import de.scisertec.admin.survey.model.SurveyType;
import de.scisertec.admin.survey.model.view.SurveyDetailedBean;
import org.joda.time.DateTime;

import java.util.Set;

public class vCongressSurveyDetailedBean extends SurveyDetailedBean implements vCongressSurveyDetailed {

    Boolean payment;
    Boolean abstractSubmission;
    Boolean reviewing;

    public vCongressSurveyDetailedBean(Long id, SurveyStatus status, SurveyType surveyType, String link, ConcreteSurveyType concreteSurveyType, String name, DateTime creationDate, DateTime expirationDate, Set<Sheet> sheets, Boolean payment, Boolean abstractSubmission, Boolean reviewing) {
        super(id, status, surveyType, link, concreteSurveyType, name, creationDate, expirationDate, sheets);
        this.payment = payment;
        this.abstractSubmission = abstractSubmission;
        this.reviewing = reviewing;
    }

    @Override
    public Boolean getPayment() {
        return payment;
    }

    @Override
    public Boolean getAbstractSubmission() {
        return abstractSubmission;
    }

    @Override
    public Boolean getReviewing() {
        return reviewing;
    }
}
