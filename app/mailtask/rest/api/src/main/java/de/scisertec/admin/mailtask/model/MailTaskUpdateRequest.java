package de.scisertec.admin.mailtask.model;

import de.scisertec.admin.mailtask.model.view.MailTaskUpdate;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

public class MailTaskUpdateRequest implements MailTaskUpdate {

    String comment;

    @NotNull
    String topic;

    @NotNull
    String senderName;
    @NotNull @Email
    String senderAddress;

    public String getComment() {
        return comment;
    }

    public String getTopic() {
        return topic;
    }

    @Override
    public String getSenderName() {
        return senderName;
    }

    @Override
    public String getSenderAddress() {
        return senderAddress;
    }

}
