package de.scisertec.admin.mailtask.model.view;

import de.scisertec.admin.core.model.view.ViewId;
import de.scisertec.admin.mailtask.model.MailJobStatus;

public interface MailJobEntry extends ViewId {

    String getEmailAddress();

    String getTitle();
    String getFirstName();
    String getLastName();

    MailJobStatus getStatus();

}
