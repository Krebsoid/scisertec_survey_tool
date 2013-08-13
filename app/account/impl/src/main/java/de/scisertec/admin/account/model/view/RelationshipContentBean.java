package de.scisertec.admin.account.model.view;

import de.scisertec.admin.account.model.Group;
import de.scisertec.admin.account.model.Role;

import java.util.HashSet;
import java.util.Set;

public class RelationshipContentBean implements RelationshipContent {

    GroupIdentifier group;
    Set<RoleName> roles = new HashSet<RoleName>();

    public RelationshipContentBean(Group group, Set<Role> roles) {
        this.group = group.asName();
        for(Role role : roles) {
            this.roles.add(role.asName());
        }
    }

    @Override
    public GroupIdentifier getGroup() {
        return group;
    }

    @Override
    public Set<RoleName> getRoles() {
        return roles;
    }
}
