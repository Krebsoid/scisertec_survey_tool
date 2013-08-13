package de.scisertec.admin.mailtask.model.view;

public class MailReceiverContentContainerBean implements MailReceiverContentContainer {

    MailReceiverContent mailReceiverContent;
    Long mailTaskId;

    @Override
    public MailReceiverContent getReceiverContent() {
        return mailReceiverContent;
    }

    @Override
    public Long getMailTaskId() {
        return mailTaskId;
    }

    @Override
    public void setReceiverContent(MailReceiverContent content) {
        this.mailReceiverContent = content;
    }

    @Override
    public void setMailTaskId(Long mailTaskId) {
        this.mailTaskId = mailTaskId;
    }
}
