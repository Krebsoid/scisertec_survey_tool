package de.scisertec.admin.account.model;

import de.scisertec.admin.account.model.view.RelationshipContent;
import de.scisertec.admin.account.model.view.RelationshipContentBean;
import de.scisertec.admin.account.qualifier.AccountPersistence;
import de.scisertec.admin.core.model.TechnicalId;

import javax.inject.Inject;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "relationship")
public class RelationshipBean implements Relationship, TechnicalId {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne(targetEntity = GroupBean.class)
    Group group;

    @ManyToMany(targetEntity = RoleBean.class)
    Set<Role> roles = new HashSet<Role>();


    @Transient
    @Inject
    @AccountPersistence
    EntityManager entityManager;


    @Override
    public Long id() {
        return id;
    }

    @Override
    public Set<Role> roles() {
        return roles;
    }

    @Override
    public Group group() {
        return group;
    }

    @Override
    public Relationship group(Group group) {
        this.group = group;
        return this;
    }

    @Override
    public Relationship addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    @Override
    public Relationship addRoles(Set<Role> roles) {
        this.roles.addAll(roles);
        return this;
    }

    @Override
    public Relationship removeRole(Role role) {
        this.roles.remove(role);
        return this;
    }

    @Override
    public Relationship save() {
        entityManager.merge(this);
        return this;
    }

    @Override
    public RelationshipContent asContent() {
        return new RelationshipContentBean(group, roles);
    }

}
