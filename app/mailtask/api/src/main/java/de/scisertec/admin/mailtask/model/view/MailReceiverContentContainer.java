package de.scisertec.admin.mailtask.model.view;

public interface MailReceiverContentContainer {

    Long getMailTaskId();
    MailReceiverContent getReceiverContent();
    void setReceiverContent(MailReceiverContent content);
    void setMailTaskId(Long mailTaskId);

}
