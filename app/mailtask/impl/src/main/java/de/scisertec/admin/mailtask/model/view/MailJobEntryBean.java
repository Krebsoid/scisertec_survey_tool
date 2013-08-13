package de.scisertec.admin.mailtask.model.view;

import de.scisertec.admin.mailtask.model.MailJobStatus;

public class MailJobEntryBean implements MailJobEntry {

    Long id;

    String emailAddress;
    String title;
    String firstName;
    String lastName;

    MailJobStatus status;

    public MailJobEntryBean(Long id, String emailAddress, String title, String firstName, String lastName, MailJobStatus status) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
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

    @Override
    public MailJobStatus getStatus() {
        return status;
    }
}
