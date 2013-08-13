package de.scisertec.admin.account.model;

import de.scisertec.admin.account.qualifier.AccountPersistence;
import org.apache.commons.lang.RandomStringUtils;

import javax.inject.Inject;
import javax.persistence.*;

@Entity
@Table(name = "credential")
public class CredentialBean implements Credential {

    @Id
    @GeneratedValue
    Long id;

    String userName;

    String password;


    @Transient
    @Inject
    @AccountPersistence
    EntityManager entityManager;


    @Override
    public String userName() {
        return userName;
    }

    @Override
    public Credential userName(String username) {
        this.userName = username;
        return this;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public Credential password(String password) {
        this.password = password;
        return this;
    }

    @Override
    public Credential randomPassword() {
        this.password = RandomStringUtils.randomAlphanumeric(8);
        return this;
    }

    @Override
    public Credential save() {
        entityManager.merge(this);
        return this;
    }
}
