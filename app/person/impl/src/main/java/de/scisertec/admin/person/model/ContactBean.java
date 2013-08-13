package de.scisertec.admin.person.model;

import de.scisertec.admin.person.qualifier.PersonPersistence;

import javax.inject.Inject;
import javax.persistence.*;

@Entity
@Table(name = "contact")
public class ContactBean implements Contact {

    @Id
    @GeneratedValue
    Long id;

    String email;
    String fax;
    String phone;

    @Transient
    @Inject
    @PersonPersistence
    EntityManager entityManager;


    @Override
    public Long id() {
        return id;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String fax() {
        return fax;
    }

    @Override
    public String phone() {
        return phone;
    }


    @Override
    public Contact email(String email) {
        this.email = email;
        return this;
    }

    @Override
    public Contact fax(String fax) {
        this.fax = fax;
        return this;
    }

    @Override
    public Contact phone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public Contact save() {
        entityManager.merge(this);
        return this;
    }
}
