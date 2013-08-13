package de.scisertec.admin.core.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "document")
public class DocumentBean implements Document {

    @Id
    @GeneratedValue
    Long id;

    byte[] data;

    String fileName;
    String extension;

    Double size;

    String locale;

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public String getExtension() {
        return extension;
    }

    @Override
    public Double getSize() {
        return size;
    }


}
