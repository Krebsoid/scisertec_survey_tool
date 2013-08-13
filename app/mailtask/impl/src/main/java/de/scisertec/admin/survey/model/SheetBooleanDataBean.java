package de.scisertec.admin.survey.model;

import javax.persistence.Entity;

@Entity
public class SheetBooleanDataBean extends SheetDataBean implements SheetBooleanData {

    Boolean booleanData;

    public SheetBooleanDataBean() {
    }

    public SheetBooleanDataBean(Boolean data) {
        this.booleanData = data;
    }

    @Override
    public Boolean data() {
        return booleanData;
    }
}
