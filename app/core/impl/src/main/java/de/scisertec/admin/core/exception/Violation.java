package de.scisertec.admin.core.exception;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class Violation {

    String key;
    String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
