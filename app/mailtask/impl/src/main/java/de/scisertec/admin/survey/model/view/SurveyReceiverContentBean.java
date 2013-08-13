package de.scisertec.admin.survey.model.view;

public class SurveyReceiverContentBean implements SurveyReceiverContent {

    Long id;

    String uuid = "";

    String salutation = "";
    String title = "";
    String firstName = "";
    String lastName = "";

    public SurveyReceiverContentBean(Long id, String uuid, String salutation, String title, String firstName, String lastName) {
        this.id = id;
        this.uuid = uuid;
        this.salutation = salutation;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getUuid() {
        return uuid;
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
