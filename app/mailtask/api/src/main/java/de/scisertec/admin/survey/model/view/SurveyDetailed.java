package de.scisertec.admin.survey.model.view;

import de.scisertec.admin.core.model.view.ViewId;
import de.scisertec.admin.survey.model.ConcreteSurveyType;
import de.scisertec.admin.survey.model.SurveyStatus;
import de.scisertec.admin.survey.model.SurveyType;
import org.joda.time.DateTime;

import java.util.List;

public interface SurveyDetailed extends ViewId {

    SurveyStatus getStatus();
    String getLink();
    SurveyType getSurveyType();
    ConcreteSurveyType getConcreteSurveyType();

    DateTime getCreationDate();
    DateTime getExpirationDate();

    Long getTimeRemaining();

    List<SheetContent> getSheets();

    String getName();

}
