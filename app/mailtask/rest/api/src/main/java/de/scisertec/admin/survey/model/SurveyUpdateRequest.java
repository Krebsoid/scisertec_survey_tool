package de.scisertec.admin.survey.model;

import de.scisertec.admin.survey.model.view.SurveyUpdate;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.joda.time.DateTime;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SurveyUpdateRequest implements SurveyUpdate {

    Long mailTaskId;
    String name;
    SurveyType surveyType;
    DateTime expirationDate;

    @Override
    public Long getMailTaskId() {
        return mailTaskId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DateTime getExpirationDate() {
        return expirationDate;
    }

    @Override
    public SurveyType getSurveyType() {
        return surveyType;
    }
}
