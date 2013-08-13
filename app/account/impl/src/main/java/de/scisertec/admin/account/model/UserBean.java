package de.scisertec.admin.account.model;

import de.scisertec.admin.account.model.view.*;
import de.scisertec.admin.account.qualifier.AccountPersistence;
import de.scisertec.admin.account.service.RelationshipCatalog;

import javax.inject.Inject;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "systemuser")
public class UserBean implements User {

    @Id
    @GeneratedValue
    Long id;

    @OneToOne(targetEntity = CredentialBean.class)
    Credential credential;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = RelationshipBean.class)
    Set<Relationship> relationships = new HashSet<Relationship>();

    @Transient
    @Inject
    @AccountPersistence
    EntityManager entityManager;

    @Transient
    @Inject
    RelationshipCatalog relationshipCatalog;

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Credential credential() {
        return credential;
    }

    @Override
    public Set<Relationship> relationships() {
        return relationships;
    }


    @Override
    public User name(String name) {
        credential().userName(name).save();
        return this;
    }

    @Override
    public User password(String password) {
        credential().password(password).save();
        return this;
    }

    @Override
    public User randomPassword() {
        credential().randomPassword().save();
        return this;
    }

    @Override
    public User addRelationship(Relationship relationship) {
        this.relationships.add(relationship);
        return this;
    }

    @Override
    public User addRelationship(Group group) {
        Relationship relationship = relationshipCatalog.create(group);
        this.relationships.add(relationship);
        return this;
    }

    @Override
    public User addRelationship(Group group, Set<Role> roles) {
        Relationship relationship = relationshipCatalog.create(group);
        this.relationships.add(relationship);
        return this;
    }

    @Override
    public User removeRelationship(Relationship relationship) {
        this.relationships.remove(relationship);
        return this;
    }

    @Override
    public User credential(Credential credential) {
        this.credential = credential;
        return this;
    }


    @Override
    public User save() {
        entityManager.merge(this);
        return this;
    }

    @Override
    public Boolean credentialCheck(Login login) {
        return credential.userName().equals(login.getUserName()) &&
               credential.password().equals(login.getPassword());
    }

    @Override
    public Boolean hasGroup(Group group) {
        for(Relationship rs : this.relationships) {
            if(group.equals(rs.group())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean hasRole(Group group, String role) {
        for(Relationship rs : this.relationships) {
            if(group.equals(rs.group())) {
                for(Role r : group.roles()) {
                    if(role.equals(r.name())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public UserState asState() {
        return new UserStateBean(id, credential().userName(), relationships);
    }
}
