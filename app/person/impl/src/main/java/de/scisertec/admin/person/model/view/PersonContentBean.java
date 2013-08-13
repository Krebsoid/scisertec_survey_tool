package de.scisertec.admin.person.model.view;

public class PersonContentBean implements PersonContent {

    Long id;

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

    public PersonContentBean(Long id, String title, String firstName, String lastName, String street, String zipCode,
                             String city, String country, String institute, String department, String position,
                             String phone, String fax, String email) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.institute = institute;
        this.department = department;
        this.position = position;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
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

    @Override
    public Long getId() {
        return id;
    }
}
