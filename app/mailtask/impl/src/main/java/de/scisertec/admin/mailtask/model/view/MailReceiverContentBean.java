package de.scisertec.admin.mailtask.model.view;

public class MailReceiverContentBean implements MailReceiverContent {

    Long id;

    String salutation = "";

    String title = "";

    String firstName = "";
    String lastName = "";

    public MailReceiverContentBean(Long id, String salutation, String title, String firstName, String lastName) {
        this.id = id;
        this.salutation = salutation;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getSalutation() {
        return salutation;
    }

    @Override
    public String getTitle() {
        return " "+title;
    }

    @Override
    public String getFirstName() {
        return " "+firstName;
    }

    @Override
    public String getLastName() {
        return " "+lastName;
    }
}
