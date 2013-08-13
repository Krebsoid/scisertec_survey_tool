package de.scisertec.admin.core.startup;

import de.scisertec.admin.core.qualifier.MailConfiguration;
import de.scisertec.admin.core.service.mail.Configuration;
import de.scisertec.admin.core.service.mail.ConfigurationBean;

import javax.enterprise.inject.Produces;

public class Mail {

    @MailConfiguration
    @Produces
    Configuration produce() {

        ConfigurationBean configuration = new ConfigurationBean();

        configuration.setHostName("smtp.1und1.de");
        configuration.setLogin("noreply@sciser.net");
        configuration.setPassword("XXXXXXXX");

        configuration.setSourceName("Dr. Daniel Wicke");
        configuration.setSourceMailAddress("team@scisertec.de");

        return configuration;

    }

}
