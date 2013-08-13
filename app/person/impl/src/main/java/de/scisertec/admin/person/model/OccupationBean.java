package de.scisertec.admin.person.model;

import de.scisertec.admin.person.qualifier.PersonPersistence;

import javax.inject.Inject;
import javax.persistence.*;

@Entity
@Table(name = "occupation")
public class OccupationBean implements Occupation {

    @Id
    @GeneratedValue
    Long id;

    String institute;
    String department;
    String position;

    @Transient
    @Inject
    @PersonPersistence
    EntityManager entityManager;


    @Override
    public Long id() {
        return id;
    }


    @Override
    public String institute() {
        return institute;
    }

    @Override
    public String department() {
        return department;
    }

    @Override
    public String position() {
        return position;
    }

    @Override
    public Occupation institute(String institute) {
        this.institute = institute;
        return this;
    }

    @Override
    public Occupation department(String department) {
        this.department = department;
        return this;
    }

    @Override
    public Occupation position(String position) {
        this.position = position;
        return this;
    }

    @Override
    public Occupation save() {
        entityManager.merge(this);
        return this;
    }
}
