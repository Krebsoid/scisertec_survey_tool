package de.scisertec.admin.mailtask.service;

import de.scisertec.admin.mailtask.model.MailJob;

public interface MailJobCatalog {

    MailJob create();

    MailJob findById(Long id);

}
