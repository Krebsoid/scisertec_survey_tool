package de.scisertec.admin.person.service;

import de.scisertec.admin.account.model.Group;
import de.scisertec.admin.person.model.Person;
import de.scisertec.admin.person.model.view.PersonCreate;

import java.util.List;

public interface PersonCatalog {

    Person create();
    Person create(PersonCreate personCreate);

    List<Person> findAll();
    List<Person> findByGroup(Group group);
    Person findById(Long id);

}
