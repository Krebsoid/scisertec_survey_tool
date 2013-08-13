package de.scisertec.admin.mailtask.model;

import de.scisertec.admin.mailtask.model.view.MailReceiverContent;
import de.scisertec.admin.mailtask.model.view.MailReceiverContentBean;
import de.scisertec.admin.mailtask.qualifier.MailTaskPersistence;
import de.scisertec.admin.person.model.Gender;
import de.scisertec.admin.person.model.Person;

import javax.inject.Inject;
import javax.persistence.*;

@Entity
@Table(name = "mailreceiver")
public class MailReceiverBean implements MailReceiver {

    @Id
    @GeneratedValue
    Long id;

    String emailAddress;

    String title = "";

    String firstName;
    String lastName;

    String salutation;


    @Transient
    @Inject
    @MailTaskPersistence
    EntityManager entityManager;


    @Override
    public Long id() {
        return id;
    }

    @Override
    public String emailAddress() {
        return emailAddress;
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
    public String salutation() {
        return salutation;
    }


    @Override
    public MailReceiver person(Person person) {
        this.emailAddress = person.contact().email();
        this.firstName = person.firstName();
        this.lastName = person.lastName();
        this.title = person.title();
        this.salutation = person.gender() == Gender.MALE ? "Sehr geehrter Herr" : "Sehr geehrte Frau";
        return this;
    }

    @Override
    public MailReceiver emailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    @Override
    public MailReceiver title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public MailReceiver salutation(String salutation) {
        this.salutation = salutation;
        return this;
    }

    @Override
    public MailReceiver name(String lastName, String firstName) {
        this.firstName = firstName;
        this.lastName = lastName;
        return this;
    }

    @Override
    public MailReceiver name(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public MailReceiver save() {
        entityManager.merge(this);
        return this;
    }

    @Override
    public MailReceiverContent asContent() {
        return new MailReceiverContentBean(id, salutation, title, firstName, lastName);
    }

}
