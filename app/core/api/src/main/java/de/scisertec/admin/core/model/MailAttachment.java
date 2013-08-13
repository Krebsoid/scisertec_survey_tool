package de.scisertec.admin.core.model;

public interface MailAttachment {

    String getFileName();
    String getDescription();
    String getContentType();

    byte[] getContent();

}
