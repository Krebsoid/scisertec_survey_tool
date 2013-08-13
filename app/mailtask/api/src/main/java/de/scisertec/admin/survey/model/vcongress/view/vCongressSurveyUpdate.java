package de.scisertec.admin.survey.model.vcongress.view;

import de.scisertec.admin.survey.model.view.SurveyUpdate;

public interface vCongressSurveyUpdate extends SurveyUpdate {

    Boolean getPayment();
    Boolean getAbstractSubmission();
    Boolean getReviewing();

}
