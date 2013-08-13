package de.scisertec.admin.survey.model.vcongress.view;

import de.scisertec.admin.survey.model.view.SurveyDetailed;

public interface vCongressSurveyDetailed extends SurveyDetailed {

    Boolean getPayment();
    Boolean getAbstractSubmission();
    Boolean getReviewing();

}
