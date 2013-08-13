package de.scisertec.admin.account.model;

import de.scisertec.admin.account.model.view.GroupIdentifier;
import de.scisertec.admin.account.model.view.GroupIdentifierBean;
import de.scisertec.admin.account.qualifier.AccountPersistence;
import de.scisertec.admin.account.service.RoleCatalog;

import javax.inject.Inject;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
public class GroupBean implements Group {

    @Id
    @GeneratedValue
    Long id;

    String identifier;
    String name;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = RoleBean.class)
    Set<Role> roles = new HashSet<Role>();

    @OneToOne(cascade = CascadeType.ALL, targetEntity = RoleBean.class)
    Role defaultRole;

    @Transient
    @Inject
    @AccountPersistence
    EntityManager entityManager;

    @Transient
    @Inject
    RoleCatalog roleCatalog;


    @Override
    public Long id() {
        return id;
    }

    @Override
    public String identifier() {
        return identifier;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Set<Role> roles() {
        return roles;
    }

    @Override
    public Role defaultRole() {
        return defaultRole;
    }

    @Override
    public Group name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Group identifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    @Override
    public Group defaultRole(Role role) {
        this.defaultRole = role;
        return this;
    }

    @Override
    public Group addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    @Override
    public Group addRole(String role) {
        Role createdRole = roleCatalog.create().name(role).save();
        this.addRole(createdRole);
        return this;
    }

    @Override
    public Group removeRole(Role role) {
        this.roles.remove(role);
        return this;
    }

    @Override
    public Group save() {
        entityManager.merge(this);
        return this;
    }


    @Override
    public GroupIdentifier asName() {
        return new GroupIdentifierBean(identifier);
    }
}
