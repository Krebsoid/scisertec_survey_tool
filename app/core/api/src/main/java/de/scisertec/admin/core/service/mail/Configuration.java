package de.scisertec.admin.core.service.mail;

public interface Configuration {

    String getHostName();
    String getLogin();
    String getPassword();

    String getSourceName();
    String getSourceMailAddress();

}
