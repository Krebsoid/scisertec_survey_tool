package de.scisertec.admin.core.exception;

public class NoResourceFoundException extends RuntimeException {

    Class clazz;
    Object id;

    public NoResourceFoundException(String message, Class clazz, Object id) {
        super(message);
        this.clazz = clazz;
        this.id = id;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Object getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
