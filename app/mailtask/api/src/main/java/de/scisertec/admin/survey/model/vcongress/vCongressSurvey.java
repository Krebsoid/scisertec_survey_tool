package de.scisertec.admin.survey.model.vcongress;

import de.scisertec.admin.survey.model.Survey;
import de.scisertec.admin.survey.model.vcongress.view.vCongressSurveyDetailed;

public interface vCongressSurvey extends Survey {

    Boolean payment();
    Boolean abstractSubmission();
    Boolean reviewing();

    vCongressSurvey payment(Boolean payment);
    vCongressSurvey abstractSubmission(Boolean abstractSubmission);
    vCongressSurvey reviewing(Boolean reviewing);

    vCongressSurveyDetailed asDetailed();

}
