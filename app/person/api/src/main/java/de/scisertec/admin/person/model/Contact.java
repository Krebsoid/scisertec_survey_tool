package de.scisertec.admin.person.model;

import de.scisertec.admin.core.model.TechnicalId;

public interface Contact extends TechnicalId {

    String email();
    String fax();
    String phone();

    Contact email(String email);
    Contact fax(String fax);
    Contact phone(String phone);

    Contact save();

}
