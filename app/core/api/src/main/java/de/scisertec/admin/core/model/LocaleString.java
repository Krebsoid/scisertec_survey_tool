package de.scisertec.admin.core.model;

import java.util.Locale;

public interface LocaleString {

    String get();
    String get(Locale locale);

    LocaleString add(Locale locale, String s);
    LocaleString remove(Locale locale);

}
