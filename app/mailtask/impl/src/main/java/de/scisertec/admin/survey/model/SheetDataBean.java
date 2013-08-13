package de.scisertec.admin.survey.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sheetdata")
public abstract class SheetDataBean implements SheetData {

    @Id
    @GeneratedValue
    Long id;

}
