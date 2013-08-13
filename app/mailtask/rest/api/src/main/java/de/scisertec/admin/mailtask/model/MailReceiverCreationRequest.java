package de.scisertec.admin.mailtask.model;

import de.scisertec.admin.mailtask.model.view.MailReceiverCreation;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class MailReceiverCreationRequest implements MailReceiverCreation {

    @NotEmpty
    @Email
    String emailAddress;

    @NotEmpty
    String salutation = "";

    String title = "";

    String firstName = "";

    @NotEmpty
    String lastName = "";

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String getSalutation() {
        return salutation;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

}
