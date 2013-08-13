package de.scisertec.admin.person.model;

import de.scisertec.admin.account.model.User;
import de.scisertec.admin.core.model.TechnicalId;
import de.scisertec.admin.person.model.view.PersonContent;
import org.joda.time.LocalDate;

public interface Person extends TechnicalId {

    User user();

    String title();
    String firstName();
    String lastName();
    Gender gender();
    LocalDate birthDay();

    Contact contact();
    Address address();
    Occupation occupation();

    Person user(User user);

    Person contact(Contact contact);
    Person email(String email);
    Person phone(String phone);
    Person fax(String fax);

    Person address(Address address);
    Person street(String street);
    Person zipCode(String zipCode);
    Person city(String city);
    Person country(String country);

    Person occupation(Occupation occupation);
    Person institute(String institute);
    Person department(String department);
    Person position(String position);

    Person title(String title);
    Person name(String firstName, String lastName);
    Person gender(Gender gender);
    Person birthDay(LocalDate birthDay);

    Person save();

    PersonContent asContent();

}
