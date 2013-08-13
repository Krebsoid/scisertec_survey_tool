package de.scisertec.admin.account.service;

import de.scisertec.admin.account.model.Group;

public interface GroupCatalog {

    Group create();

    Group findById(Long id);
    Group findByIdentifier(String identifier);

}
