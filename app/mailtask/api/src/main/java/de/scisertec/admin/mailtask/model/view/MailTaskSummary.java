package de.scisertec.admin.mailtask.model.view;

import de.scisertec.admin.core.model.view.ViewId;
import de.scisertec.admin.mailtask.model.MailTaskStatus;
import org.joda.time.DateTime;

public interface MailTaskSummary extends ViewId {

    String getComment();

    String getTopic();
    Boolean getContentAvailable();

    MailTaskStatus getStatus();

    DateTime getCreationDate();

    Integer getMailJobNumber();

}
