package de.scisertec.admin.account.model;

import de.scisertec.admin.account.model.view.RelationshipContent;

import java.util.Set;

public interface Relationship {

    Set<Role> roles();
    Group group();

    Relationship group(Group group);
    Relationship addRole(Role role);
    Relationship addRoles(Set<Role> roles);
    Relationship removeRole(Role role);

    Relationship save();

    RelationshipContent asContent();

}
