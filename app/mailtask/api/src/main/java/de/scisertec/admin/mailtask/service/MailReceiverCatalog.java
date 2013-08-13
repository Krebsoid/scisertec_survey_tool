package de.scisertec.admin.mailtask.service;

import de.scisertec.admin.mailtask.model.MailReceiver;
import de.scisertec.admin.mailtask.model.view.MailReceiverCreation;

public interface MailReceiverCatalog {

    MailReceiver create();

    MailReceiver create(MailReceiverCreation mailReceiverCreation);

    MailReceiver findById(Long id);
}
