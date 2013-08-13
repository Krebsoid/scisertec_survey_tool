package de.scisertec.admin.core.model;

public interface Document extends TechnicalId {

    String getFileName();
    String getExtension();

    Double getSize();

}
