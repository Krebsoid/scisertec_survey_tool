package de.scisertec.admin.mailtask.model;

import de.scisertec.admin.core.model.TechnicalId;
import de.scisertec.admin.mailtask.model.view.MailJobEntry;

public interface MailJob extends TechnicalId {

    MailReceiver receiver();
    MailJobStatus status();

    MailJobEntry asEntry();

    MailJob receiver(MailReceiver receiver);
    MailJob status(MailJobStatus status);
    MailJob save();

}
