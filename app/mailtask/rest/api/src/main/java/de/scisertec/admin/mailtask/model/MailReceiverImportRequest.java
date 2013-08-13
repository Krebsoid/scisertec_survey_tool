package de.scisertec.admin.mailtask.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;


public class MailReceiverImportRequest {

    @NotEmpty
    @Size(max = 5242880)
    @FormParam("importFile")
    @PartType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    byte[] importFile;

    public byte[] getImportFile() {
        return importFile;
    }
}
