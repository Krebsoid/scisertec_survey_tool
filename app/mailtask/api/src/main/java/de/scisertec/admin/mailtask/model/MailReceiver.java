package de.scisertec.admin.mailtask.model;

import de.scisertec.admin.core.model.TechnicalId;
import de.scisertec.admin.mailtask.model.view.MailReceiverContent;
import de.scisertec.admin.person.model.Person;

public interface MailReceiver extends TechnicalId {

    String emailAddress();

    String title();

    String firstName();
    String lastName();

    String salutation();

    MailReceiver person(Person person);

    MailReceiver emailAddress(String emailAddress);
    MailReceiver title(String title);
    MailReceiver salutation(String salutation);
    MailReceiver name(String lastName, String firstName);
    MailReceiver name(String lastName);
    MailReceiver save();

    MailReceiverContent asContent();

}
