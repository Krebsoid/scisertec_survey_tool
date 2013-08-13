package de.scisertec.admin.survey.model.scisertec;

import de.scisertec.admin.account.model.Group;
import de.scisertec.admin.survey.model.Survey;

public interface SciSerTecSurvey extends Survey {

    Group group();

    SciSerTecSurvey group(Group group);

}
