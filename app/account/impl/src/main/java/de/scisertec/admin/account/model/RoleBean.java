package de.scisertec.admin.account.model;

import de.scisertec.admin.account.model.view.RoleName;
import de.scisertec.admin.account.model.view.RoleNameBean;
import de.scisertec.admin.account.qualifier.AccountPersistence;

import javax.inject.Inject;
import javax.persistence.*;

@Entity
@Table(name = "role")
public class RoleBean implements Role {

    @Id
    @GeneratedValue
    Long id;

    String name;


    @Transient
    @Inject
    @AccountPersistence
    EntityManager entityManager;


    @Override
    public Long id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Role name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Role save() {
        entityManager.merge(this);
        return this;
    }


    @Override
    public RoleName asName() {
        return new RoleNameBean(name);
    }
}
