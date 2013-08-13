package de.scisertec.admin.account.model;

public interface UserGroup {

    UserGroup withRoleAnd(Role role);
    User withRole(Role role);

}
