package de.scisertec.admin.mailtask.model;

import de.scisertec.admin.mailtask.model.view.MailTaskTest;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

public class MailTaskTestRequest implements MailTaskTest {

    @Email
    @NotNull
    String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }
}
