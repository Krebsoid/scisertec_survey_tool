package de.scisertec.admin.mailtask.service;

import de.scisertec.admin.core.service.mail.MailService;
import de.scisertec.admin.mailtask.model.MailReceiver;
import de.scisertec.admin.mailtask.model.MailReceiverBean;
import de.scisertec.admin.mailtask.model.MailTask;
import de.scisertec.admin.mailtask.model.MailTaskStatus;
import de.scisertec.admin.mailtask.model.view.MailReceiverContentContainer;
import de.scisertec.admin.mailtask.model.view.MailReceiverContentContainerBean;
import de.scisertec.admin.mailtask.qualifier.TestMail;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@ApplicationScoped
public class MailTaskDeliveryBean implements MailTaskDelivery {

    @Inject
    MailService mailService;

    @Inject
    MailTaskCatalog mailTaskCatalog;

    @Inject
    MailTaskTemplateFactory mailTaskTemplateFactory;

    @Inject
    @TestMail
    Event<MailReceiverContentContainer> containerEvent;

    @EJB
    MailTaskWorker mailTaskWorker;

    @Override
    public void test(MailTask mailTask, String mailAddress) {
        if(mailAddress != null) {
            String contentAsHtml = testMailContent(mailTask);
            mailService.setContent(contentAsHtml)
                    .setTopic(mailTask.topic())
                    .setReceiver(mailAddress)
                    .setSender(mailTask.senderAddress(), mailTask.senderName())
                    .sendMail();
        }
    }

    @Override
    public String testMailContent(MailTask mailTask) {
        byte[] mailTaskContent = mailTask.template();
        if(mailTaskContent.length > 0) {
            MailReceiver receiverBean = new MailReceiverBean();
            receiverBean.salutation("Sehr geehrter Herr").name(" Bruweleit", " Malte");
            MailReceiverContentContainer mailReceiverContainer = new MailReceiverContentContainerBean();
            mailReceiverContainer.setReceiverContent(receiverBean.asContent());
            mailReceiverContainer.setMailTaskId(mailTask.id());

            containerEvent.fire(mailReceiverContainer);

            return mailTaskTemplateFactory.processTemplate(mailTaskContent, mailReceiverContainer.getReceiverContent());
        }
        else {
            return null;
        }
    }

    @Override
    public void start(MailTask mailTask) {
        if(mailTask.contentAvailable() && mailTask.mailJobs().size() > 0) {
            mailTask.status(MailTaskStatus.RUNNING).save();
            mailTaskWorker.startMailTask(mailTask, mailTaskTemplateFactory);
        }
        else {
            mailTask.status(MailTaskStatus.FAILED).save();
        }
    }

}
