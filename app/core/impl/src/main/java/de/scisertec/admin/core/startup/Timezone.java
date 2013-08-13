package de.scisertec.admin.core.startup;


import org.jboss.seam.international.timezone.DefaultTimeZone;

import javax.enterprise.inject.Produces;


public class Timezone {

    @Produces
    @DefaultTimeZone
    String defaultTimeZoneId = "Europe/Amsterdam";

}
