package de.scisertec.admin.core.model;


import javax.inject.Inject;
import javax.persistence.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Entity
@Table(name = "localestring")
public class LocaleStringBean implements LocaleString {

    @Id
    @GeneratedValue
    Long id;

    @Version
    Integer version;

    @ElementCollection
    public Map<Locale, String> internal = new HashMap<Locale, String>();

    @Inject
    @Transient
    protected Locale locale;

    public String get() {
        return internal.get(locale);
    }

    public String get(Locale locale) {
        return internal.get(locale);
    }

    public LocaleString add(Locale locale, String s) {
        internal.put(locale, s);
        return this;
    }

    public LocaleString remove(Locale locale) {
        internal.remove(locale);
        return this;
    }

}
