package de.scisertec.admin.account.model;

import de.scisertec.admin.account.model.view.GroupIdentifier;
import de.scisertec.admin.core.model.TechnicalId;

import java.util.Set;

public interface Group extends TechnicalId {

    String identifier();

    String name();
    Set<Role> roles();
    Role defaultRole();

    Group name(String name);
    Group identifier(String identifier);
    Group defaultRole(Role role);
    Group addRole(Role role);
    Group addRole(String role);
    Group removeRole(Role role);

    Group save();

    GroupIdentifier asName();

}
