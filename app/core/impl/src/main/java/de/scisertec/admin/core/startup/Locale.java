package de.scisertec.admin.core.startup;


import org.jboss.seam.international.locale.DefaultLocale;

import javax.enterprise.inject.Produces;


public class Locale {

    @Produces
    @DefaultLocale
    String defaultLocaleKey = "en";

}
