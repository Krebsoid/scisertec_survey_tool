package de.scisertec.admin.survey.model.view;

import de.scisertec.admin.core.model.view.ViewId;
import de.scisertec.admin.survey.model.ConcreteSurveyType;
import de.scisertec.admin.survey.model.SurveyStatus;
import de.scisertec.admin.survey.model.SurveyType;
import org.joda.time.DateTime;

public interface SurveySummary extends ViewId {

    String getName();

    SurveyType getSurveyType();
    ConcreteSurveyType getConcreteSurveyType();
    SurveyStatus getStatus();

    Integer getSheetCount();
    Integer getFilledSheetCount();

    DateTime getCreationDate();

}
