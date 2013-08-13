package de.scisertec.admin.survey.service;

import de.scisertec.admin.survey.model.ConcreteSurveyType;
import de.scisertec.admin.survey.model.Sheet;

public interface SheetCatalog {

    Sheet create(ConcreteSurveyType surveyType);

    Sheet findByUuid(String uuid);

}
