package de.scisertec.admin.survey.model.vcongress;

import de.scisertec.admin.survey.model.SheetBean;
import de.scisertec.admin.survey.model.vcongress.view.vCongressSheetContent;
import de.scisertec.admin.survey.model.vcongress.view.vCongressSheetContentBean;

import javax.persistence.Entity;

@Entity
public class vCongressSheetBean extends SheetBean implements vCongressSheet {

    Boolean payment = false;
    Boolean abstractSubmission = false;
    Boolean reviewing = false;

    public vCongressSheetBean() {
        generateUuid();
    }

    @Override
    public vCongressSheetContent asContent() {
        return new vCongressSheetContentBean(id, submissionDate, uuid(), data(), payment, abstractSubmission, reviewing);
    }


    public vCongressSheet self() {
        return this;
    }

    @Override
    public Boolean payment() {
        return payment;
    }

    @Override
    public Boolean abstractSubmission() {
        return abstractSubmission;
    }

    @Override
    public Boolean reviewing() {
        return reviewing;
    }

    @Override
    public vCongressSheet payment(Boolean payment) {
        this.payment = payment;
        return this;
    }

    @Override
    public vCongressSheet abstractSubmission(Boolean abstractSubmission) {
        this.abstractSubmission = abstractSubmission;
        return this;
    }

    @Override
    public vCongressSheet reviewing(Boolean reviewing) {
        this.reviewing = reviewing;
        return this;
    }
}
