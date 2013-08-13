package de.scisertec.admin.survey.model.vcongress.view;

import de.scisertec.admin.survey.model.view.SheetContent;

public interface vCongressSheetContent extends SheetContent {

    Boolean getPayment();
    Boolean getAbstractSubmission();
    Boolean getReviewing();

    void setPayment(Boolean payment);
    void setAbstractSubmission(Boolean abstractSubmission);
    void setReviewing(Boolean reviewing);

}
