package de.scisertec.admin.person.model;

import de.scisertec.admin.account.model.User;
import de.scisertec.admin.account.service.UserCatalog;
import de.scisertec.admin.person.model.view.PersonContent;
import de.scisertec.admin.person.model.view.PersonContentBean;
import de.scisertec.admin.person.qualifier.PersonPersistence;
import org.joda.time.LocalDate;

import javax.inject.Inject;
import javax.persistence.*;

@Entity
@Table(name = "person")
public class PersonBean implements Person {

    @Id
    @GeneratedValue
    Long id;

    Long userId;

    String title;
    String firstName;
    String lastName;

    @Enumerated
    Gender gender;

    LocalDate birthDay;

    @OneToOne(targetEntity = ContactBean.class, cascade = CascadeType.ALL)
    Contact contact;

    @OneToOne(targetEntity = OccupationBean.class, cascade = CascadeType.ALL)
    Occupation occupation;

    @OneToOne(targetEntity = AddressBean.class, cascade = CascadeType.ALL)
    Address address;


    @Transient
    @Inject
    UserCatalog userCatalog;

    @Transient
    @Inject
    @PersonPersistence
    EntityManager entityManager;


    @Override
    public Long id() {
        return id;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public String firstName() {
        return firstName;
    }

    @Override
    public String lastName() {
        return lastName;
    }

    @Override
    public Gender gender() {
        return gender;
    }

    @Override
    public LocalDate birthDay() {
        return birthDay;
    }

    @Override
    public User user() {
        return userCatalog.findById(userId);
    }

    @Override
    public Contact contact() {
        return contact;
    }

    @Override
    public Address address() {
        return address;
    }

    @Override
    public Occupation occupation() {
        return occupation;
    }



    @Override
    public Person user(User user) {
        this.userId = user.id();
        return this;
    }

    @Override
    public Person contact(Contact contact) {
        this.contact = contact;
        return this;
    }

    @Override
    public Person email(String email) {
        this.contact().email(email).save();
        return this;
    }

    @Override
    public Person phone(String phone) {
        this.contact().phone(phone).save();
        return this;
    }

    @Override
    public Person fax(String fax) {
        this.contact().fax(fax).save();
        return this;
    }

    @Override
    public Person address(Address address) {
        this.address = address;
        return this;
    }

    @Override
    public Person street(String street) {
        this.address().street(street).save();
        return this;
    }

    @Override
    public Person zipCode(String zipCode) {
        this.address().zipCode(zipCode).save();
        return this;
    }

    @Override
    public Person city(String city) {
        this.address().city(city).save();
        return this;
    }

    @Override
    public Person country(String country) {
        this.address().country(country).save();
        return this;
    }

    @Override
    public Person occupation(Occupation occupation) {
        this.occupation = occupation;
        return this;
    }

    @Override
    public Person institute(String institute) {
        this.occupation().institute(institute).save();
        return this;
    }

    @Override
    public Person department(String department) {
        this.occupation().department(department).save();
        return this;
    }

    @Override
    public Person position(String position) {
        this.occupation().position(position).save();
        return this;
    }


    @Override
    public Person title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public Person name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        return this;
    }

    @Override
    public Person gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    @Override
    public Person birthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
        return this;
    }

    @Override
    public Person save() {
        entityManager.merge(this);
        return this;
    }

    @Override
    public PersonContent asContent() {
        return new PersonContentBean(id, title, firstName, lastName, address.street(), address.zipCode(),
                address.city(), address.country(), occupation.institute(), occupation.department(),
                occupation.position(), contact.phone(), contact.fax(), contact.email());
    }
}
