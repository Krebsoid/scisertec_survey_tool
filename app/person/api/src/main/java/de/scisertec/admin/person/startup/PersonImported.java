package de.scisertec.admin.person.startup;

import de.scisertec.admin.person.model.Person;

public class PersonImported {
    public Person user;
    public Person admin;

    public PersonImported() {
    }

    public PersonImported(Person user, Person admin) {
        this.user = user;
        this.admin = admin;
    }
}
