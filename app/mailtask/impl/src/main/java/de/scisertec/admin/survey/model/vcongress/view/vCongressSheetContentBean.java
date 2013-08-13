package de.scisertec.admin.survey.model.vcongress.view;

import de.scisertec.admin.survey.model.SheetData;
import de.scisertec.admin.survey.model.SheetDataBean;
import de.scisertec.admin.survey.model.view.SheetContentBean;
import org.joda.time.DateTime;

import java.util.Map;

public class vCongressSheetContentBean extends SheetContentBean implements vCongressSheetContent {

    Boolean payment;
    Boolean abstractSubmission;
    Boolean reviewing;

    public vCongressSheetContentBean(Long id, DateTime submissionDate, String uuid, Map<String, SheetData> data, Boolean payment, Boolean abstractSubmission, Boolean reviewing) {
        super(id, submissionDate, uuid, data);
        this.payment = payment;
        this.abstractSubmission = abstractSubmission;
        this.reviewing = reviewing;
    }

    @Override
    public Boolean getReviewing() {
        return reviewing;
    }

    @Override
    public Boolean getAbstractSubmission() {
        return abstractSubmission;
    }

    @Override
    public Boolean getPayment() {
        return payment;
    }

    @Override
    public void setPayment(Boolean payment) {
        this.payment = payment;
    }

    @Override
    public void setAbstractSubmission(Boolean abstractSubmission) {
        this.abstractSubmission = abstractSubmission;
    }

    @Override
    public void setReviewing(Boolean reviewing) {
        this.reviewing = reviewing;
    }
}
