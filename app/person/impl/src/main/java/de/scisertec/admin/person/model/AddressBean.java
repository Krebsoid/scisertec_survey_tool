package de.scisertec.admin.person.model;

import de.scisertec.admin.person.qualifier.PersonPersistence;

import javax.inject.Inject;
import javax.persistence.*;

@Entity
@Table(name = "address")
public class AddressBean implements Address {

    @Id
    @GeneratedValue
    Long id;

    String street;
    String zipCode;
    String city;
    String country;

    @Transient
    @Inject
    @PersonPersistence
    EntityManager entityManager;


    @Override
    public Long id() {
        return id;
    }


    @Override
    public String street() {
        return street;
    }

    @Override
    public String zipCode() {
        return zipCode;
    }

    @Override
    public String city() {
        return city;
    }

    @Override
    public String country() {
        return country;
    }

    @Override
    public Address street(String street) {
        this.street = street;
        return this;
    }

    @Override
    public Address zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    @Override
    public Address city(String city) {
        this.city = city;
        return this;
    }

    @Override
    public Address country(String country) {
        this.country = country;
        return this;
    }

    @Override
    public Address save() {
        entityManager.merge(this);
        return this;
    }
}
