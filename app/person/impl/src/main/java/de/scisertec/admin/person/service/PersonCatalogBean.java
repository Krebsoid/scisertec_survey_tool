package de.scisertec.admin.person.service;

import de.scisertec.admin.account.model.Group;
import de.scisertec.admin.account.model.User;
import de.scisertec.admin.account.service.UserCatalog;
import de.scisertec.admin.person.model.*;
import de.scisertec.admin.person.model.view.PersonCreate;
import de.scisertec.admin.person.qualifier.PersonPersistence;
import de.scisertec.admin.person.service.jpa.PersonQueryBuilder;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class PersonCatalogBean implements PersonCatalog {

    @Inject
    @PersonPersistence
    EntityManager entityManager;

    @Inject
    UserCatalog userCatalog;

    @Inject
    Instance<PersonQueryBuilder> queryBuilder;

    public Person create() {
        ContactBean contact = new ContactBean();
        entityManager.persist(contact);

        OccupationBean occupation = new OccupationBean();
        entityManager.persist(occupation);

        AddressBean address = new AddressBean();
        entityManager.persist(address);

        PersonBean personBean = new PersonBean();
        personBean.contact(contact).address(address).occupation(occupation);
        entityManager.persist(personBean);

        entityManager.flush();

        return personBean;
    }

    public Person create(PersonCreate p) {
        User user = userCatalog.create().name(p.getEmail()).randomPassword().save();

        Person person = create().title(p.getTitle()).name(p.getFirstName(), p.getLastName())
                .street(p.getStreet()).zipCode(p.getZipCode()).city(p.getCity()).country(p.getCountry())
                .institute(p.getInstitute()).department(p.getDepartment()).position(p.getPosition())
                .email(p.getEmail()).phone(p.getPhone()).fax(p.getFax())
                .user(user).save();

        return person;
    }

    @Override
    public List<Person> findAll() {
        return queryBuilder.get().select(PersonBean.class).findAll();
    }

    @Override
    public List<Person> findByGroup(Group group) {
        List<User> users = userCatalog.findByGroup(group);
        List<Person> result = new ArrayList<Person>();
        for(User user : users) {
            Person person = queryBuilder.get().select(PersonBean.class)
                    .where()
                    .equal(PersonBean_.userId, user.id())
                    .find();
            result.add(person);
        }
        return result;
    }

    @Override
    public Person findById(Long id) {
        return queryBuilder.get().select(PersonBean.class).where().equal(PersonBean_.id, id).find();
    }

}
