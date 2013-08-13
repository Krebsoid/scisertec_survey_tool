package de.scisertec.admin.account.model;

public interface Credential {

    String userName();
    String password();

    Credential userName(String userName);
    Credential password(String password);
    Credential randomPassword();

    Credential save();

}
