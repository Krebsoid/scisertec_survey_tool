package de.scisertec.admin.account.security;

import org.jboss.seam.security.annotations.SecurityBindingType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@SecurityBindingType
@Target({FIELD, METHOD, TYPE, PARAMETER})
@Retention(RUNTIME)
public @interface LoggedOut {
}
