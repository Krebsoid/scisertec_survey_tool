package de.scisertec.admin.core.service.mail;


import org.jboss.solder.core.Veto;

@Veto
public class ConfigurationBean implements Configuration {

    String hostName;
    String login;
    String password;

    String sourceMailAddress;
    String sourceName;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSourceMailAddress() {
        return sourceMailAddress;
    }

    public void setSourceMailAddress(String sourceMailAddress) {
        this.sourceMailAddress = sourceMailAddress;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
}
