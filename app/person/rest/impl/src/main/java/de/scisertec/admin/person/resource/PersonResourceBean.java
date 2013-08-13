package de.scisertec.admin.person.resource;

import de.scisertec.admin.account.model.Group;
import de.scisertec.admin.account.service.GroupCatalog;
import de.scisertec.admin.person.model.Person;
import de.scisertec.admin.person.model.view.PersonContent;
import de.scisertec.admin.person.service.PersonCatalog;

import javax.inject.Inject;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;

public class PersonResourceBean implements PersonResource {


    @Inject
    PersonCatalog personCatalog;

    @Inject
    GroupCatalog groupCatalog;

    @Override
    public List<PersonContent> readAll() {
        List<Person> persons = personCatalog.findAll();
        List<PersonContent> personContents = new ArrayList<PersonContent>();
        for(Person person : persons) {
            personContents.add(person.asContent());
        }
        return personContents;
    }

    @Override
    public PersonContent read(@PathParam("id") Long personId) {
        Person person = personCatalog.findById(personId);
        return person.asContent();
    }

    @Override
    public List<PersonContent> readByGroup(@PathParam("groupIdentifier") String groupIdentifier) {
        Group group = groupCatalog.findByIdentifier(groupIdentifier);
        List<Person> persons = personCatalog.findByGroup(group);
        List<PersonContent> personContents = new ArrayList<PersonContent>();
        for(Person person : persons) {
            personContents.add(person.asContent());
        }
        return personContents;
    }
}
