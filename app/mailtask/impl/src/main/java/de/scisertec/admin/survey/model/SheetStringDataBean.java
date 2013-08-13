package de.scisertec.admin.survey.model;

import javax.persistence.Entity;

@Entity
public class SheetStringDataBean extends SheetDataBean implements SheetStringData {

    String stringData;

    public SheetStringDataBean() {
    }

    public SheetStringDataBean(String data) {
        this.stringData = data;
    }

    @Override
    public String data() {
        return stringData;
    }
}
