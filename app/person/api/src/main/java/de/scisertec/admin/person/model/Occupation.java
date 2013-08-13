package de.scisertec.admin.person.model;

import de.scisertec.admin.core.model.TechnicalId;

public interface Occupation extends TechnicalId {

    String institute();
    String department();
    String position();

    Occupation institute(String institute);
    Occupation department(String department);
    Occupation position(String position);

    Occupation save();

}
