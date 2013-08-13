package de.scisertec.admin.account.service;

import de.scisertec.admin.account.model.Role;

public interface RoleCatalog {

    Role create();

    Role findById(Long id);

}
