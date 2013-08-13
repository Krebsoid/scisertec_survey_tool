package de.scisertec.admin.person.model;

import de.scisertec.admin.core.model.TechnicalId;

public interface Address extends TechnicalId {

    String street();
    String zipCode();
    String city();
    String country();

    Address street(String street);
    Address zipCode(String zipCode);
    Address city(String city);
    Address country(String country);

    Address save();

}
