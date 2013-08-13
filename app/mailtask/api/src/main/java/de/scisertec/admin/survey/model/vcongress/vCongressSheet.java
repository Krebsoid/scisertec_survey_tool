package de.scisertec.admin.survey.model.vcongress;

import de.scisertec.admin.survey.model.Sheet;

public interface vCongressSheet extends Sheet {

    Boolean payment();
    Boolean abstractSubmission();
    Boolean reviewing();

    vCongressSheet payment(Boolean payment);
    vCongressSheet abstractSubmission(Boolean abstractSubmission);
    vCongressSheet reviewing(Boolean reviewing);

}
