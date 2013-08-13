package de.scisertec.admin.mailtask.model.view;

import de.scisertec.admin.core.model.view.ViewId;
import de.scisertec.admin.mailtask.model.MailTaskStatus;
import org.joda.time.DateTime;

import java.util.List;

public interface MailTaskDetailed extends ViewId {

    String getComment();
    String getTopic();
    String getSenderName();
    String getSenderAddress();

    Boolean getContentAvailable();

    MailTaskStatus getStatus();

    DateTime getCreationDate();
    List<MailJobEntry> getMailJobs();

}
