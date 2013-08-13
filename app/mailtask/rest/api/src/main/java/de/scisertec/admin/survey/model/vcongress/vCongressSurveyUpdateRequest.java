package de.scisertec.admin.survey.model.vcongress;

import de.scisertec.admin.survey.model.SurveyUpdateRequest;
import de.scisertec.admin.survey.model.vcongress.view.vCongressSurveyUpdate;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class vCongressSurveyUpdateRequest extends SurveyUpdateRequest implements vCongressSurveyUpdate {

    Boolean payment;
    Boolean abstractSubmission;
    Boolean reviewing;

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
