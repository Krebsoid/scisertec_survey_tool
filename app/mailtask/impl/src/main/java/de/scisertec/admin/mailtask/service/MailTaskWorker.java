package de.scisertec.admin.mailtask.service;

import de.scisertec.admin.mailtask.model.MailJob;
import de.scisertec.admin.mailtask.model.MailJobStatus;
import de.scisertec.admin.mailtask.model.MailTask;
import de.scisertec.admin.mailtask.model.MailTaskStatus;
import de.scisertec.admin.mailtask.model.view.MailReceiverContentContainer;
import de.scisertec.admin.mailtask.model.view.MailReceiverContentContainerBean;
import de.scisertec.admin.mailtask.qualifier.LiveMail;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.io.Serializable;

@Stateless
@Local(MailTaskWorker.class)
public class MailTaskWorker implements Serializable {

    @EJB
    MailJobWorker mailJobWorker;

    @Inject
    MailTaskCatalog mailTaskCatalog;

    @Inject
    @LiveMail
    Event<MailReceiverContentContainer> containerEvent;

    @Asynchronous
    public void startMailTask(MailTask incomingMailTask, MailTaskTemplateFactory templateFactory) {
        MailTask mailTask = mailTaskCatalog.findById(incomingMailTask.id());
        byte[] mailTaskContent = mailTask.template();

        for(MailJob mailJob : mailTask.mailJobs()) {
            MailReceiverContentContainer mailReceiverContainer = new MailReceiverContentContainerBean();
            mailReceiverContainer.setReceiverContent(mailJob.receiver().asContent());
            mailReceiverContainer.setMailTaskId(mailTask.id());

            containerEvent.fire(mailReceiverContainer);

            String contentAsHtml = templateFactory.processTemplate(mailTaskContent, mailReceiverContainer.getReceiverContent());
            startMailJob(mailJob, mailTask.topic(), contentAsHtml, mailTask.senderName(), mailTask.senderAddress());
        }

        Boolean isMailTaskDone = Boolean.TRUE;
        for(MailJob mailJob : mailTask.mailJobs()) {
            if(mailJob.status() == MailJobStatus.FAILED) {
                isMailTaskDone = Boolean.FALSE;
            }
        }

        if(isMailTaskDone) {
            mailTask.status(MailTaskStatus.DONE).save();
        }
        else {
            mailTask.status(MailTaskStatus.FAILED).save();
        }

    }


    private void startMailJob(MailJob mailJob, String content, String topic, String senderName, String senderAddress) {
        if(mailJob.status() != MailJobStatus.DONE) {
            mailJobWorker.sendMail(mailJob, content, topic, senderName, senderAddress);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
