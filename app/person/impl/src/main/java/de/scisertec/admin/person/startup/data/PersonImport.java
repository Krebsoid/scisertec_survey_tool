package de.scisertec.admin.person.startup.data;

import de.scisertec.admin.account.model.User;
import de.scisertec.admin.person.model.Gender;
import de.scisertec.admin.person.model.Person;
import de.scisertec.admin.person.service.PersonCatalog;
import org.jboss.seam.transaction.Transactional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PersonImport {

    @Inject
    PersonCatalog personCatalog;

    Person userPerson;
    Person adminPerson;

    @Transactional
    public void initialize(User user, User admin) {

        adminPerson = personCatalog.create()
                .name("Malte", "Bruweleit")
                .gender(Gender.MALE)
                .user(admin)
                .email("bruweleit_malte@team-neotech.de")
                .save();

        userPerson = personCatalog.create()
                .name("Daniel", "Wicke")
                .gender(Gender.MALE)
                .title("Dr.")
                .user(user)
                .email("contact@dwwebsites.de")
                .save();

    }


    public Person getUser() {
        return userPerson;
    }

    public Person getAdmin() {
        return adminPerson;
    }

}
