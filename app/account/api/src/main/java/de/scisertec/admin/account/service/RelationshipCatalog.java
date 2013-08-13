package de.scisertec.admin.account.service;

import de.scisertec.admin.account.model.Group;
import de.scisertec.admin.account.model.Relationship;
import de.scisertec.admin.account.model.Role;

import java.util.Set;

public interface RelationshipCatalog {

    Relationship create(Group group);
    Relationship create(Group group, Set<Role> roles);

    Relationship findById(Long id);

}
