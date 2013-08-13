package de.scisertec.admin.survey.model;

import de.scisertec.admin.person.model.view.PersonCreate;

import javax.validation.constraints.NotNull;

public class PersonCreateRequest implements PersonCreate {

    String title;
    String firstName;
    String lastName;

    String street;
    String zipCode;
    String city;
    String country;

    String institute;
    String department;
    String position;

    String phone;
    String fax;
    String email;

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
    public String getStreet() {
        return street;
    }

    @Override
    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public String getInstitute() {
        return institute;
    }

    @Override
    public String getDepartment() {
        return department;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public String getFax() {
        return fax;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public Boolean isToSave() {
        return email != null || lastName != null || phone != null ||
                city != null || fax != null || institute != null ||
                department != null;
    }
}
