package de.scisertec.admin.survey.model;

import javax.persistence.Entity;

@Entity
public class SheetIntegerDataBean extends SheetDataBean implements SheetIntegerData {

    Integer integerData;

    public SheetIntegerDataBean() {
    }

    public SheetIntegerDataBean(Integer data) {
        this.integerData = data;
    }

    @Override
    public Integer data() {
        return integerData;
    }
}
