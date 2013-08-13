package de.scisertec.admin.account.model;

import de.scisertec.admin.account.model.view.Login;
import de.scisertec.admin.account.model.view.UserState;
import de.scisertec.admin.core.model.TechnicalId;

import java.util.Set;

public interface User extends TechnicalId {

    Credential credential();
    Set<Relationship> relationships();

    User credential(Credential credential);
    User name(String name);
    User password(String password);
    User randomPassword();
    User addRelationship(Relationship relationship);
    User addRelationship(Group group);
    User addRelationship(Group group, Set<Role> roles);
    User removeRelationship(Relationship relationship);

    User save();

    Boolean credentialCheck(Login login);
    Boolean hasGroup(Group group);
    Boolean hasRole(Group group, String role);

    UserState asState();

}
