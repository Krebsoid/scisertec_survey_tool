package de.scisertec.admin.mailtask.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;


public class MailTemplateImportRequest {

    @NotEmpty
    @Size(max = 5242880)
    @FormParam("importTemplate")
    @PartType("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
    byte[] importTemplate;

    public byte[] getImportFile() {
        return importTemplate;
    }
}
