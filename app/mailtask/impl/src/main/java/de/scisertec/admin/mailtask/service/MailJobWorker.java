package de.scisertec.admin.mailtask.service;

import de.scisertec.admin.core.service.mail.MailService;
import de.scisertec.admin.mailtask.model.MailJob;
import de.scisertec.admin.mailtask.model.MailJobStatus;

import javax.ejb.*;
import javax.inject.Inject;
import java.io.Serializable;

@Stateless
@Local(MailJobWorker.class)
public class MailJobWorker implements Serializable {

    @Inject
    MailService mailService;

    @Asynchronous
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    void sendMail(MailJob mailJob, String topic, String content, String senderName, String senderAddress) {
        Boolean success = mailService.setContent(content)
                .setTopic(topic)
                .setReceiver(mailJob.receiver().emailAddress(), mailJob.receiver().firstName()+ " " +mailJob.receiver().lastName())
                .setSender(senderAddress, senderName)
                .sendMail();

        if(success) {
            mailJob.status(MailJobStatus.DONE).save();
        }
        else {
            mailJob.status(MailJobStatus.FAILED).save();
        }
    }

}
