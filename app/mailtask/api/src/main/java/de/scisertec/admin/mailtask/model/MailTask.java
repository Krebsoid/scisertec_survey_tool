package de.scisertec.admin.mailtask.model;

import de.scisertec.admin.core.model.TechnicalId;
import de.scisertec.admin.mailtask.model.view.MailTaskSummary;
import de.scisertec.admin.mailtask.model.view.MailTaskDetailed;
import de.scisertec.admin.mailtask.model.view.MailTaskUpdate;
import org.joda.time.DateTime;

import java.util.List;

public interface MailTask extends TechnicalId {

    String comment();
    String topic();
    String senderName();
    String senderAddress();

    Boolean contentAvailable();
    byte[] template();

    List<MailJob> mailJobs();

    DateTime creationDate();
    MailTaskStatus status();



    MailTask update(MailTaskUpdate changeRequest);

    MailTask topic(String topic);
    MailTask comment(String comment);
    MailTask senderName(String senderName);
    MailTask senderAddress(String senderAddress);
    MailTask status(MailTaskStatus status);

    MailTask template(byte[] content);

    MailTask receiver(List<MailReceiver> mailReceiver);
    MailTask receiver(MailReceiver mailReceiver);

    MailTask removeMailJob(MailJob mailJob);
    MailTask removeAllMailJobs();

    MailTask save();


    MailTaskSummary asSummary();
    MailTaskDetailed asDetailed();

}
