package de.scisertec.admin.survey.service;

import de.scisertec.admin.mailtask.qualifier.MailTaskPersistence;
import de.scisertec.admin.survey.model.SheetBooleanDataBean;
import de.scisertec.admin.survey.model.SheetData;
import de.scisertec.admin.survey.model.SheetIntegerDataBean;
import de.scisertec.admin.survey.model.SheetStringDataBean;
import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class SheetDataFactoryBean {

    @Inject
    @MailTaskPersistence
    EntityManager entityManager;

    public SheetData create(Object value) {
        if(value instanceof String) {
            String data = (String) value;
            data = StringUtils.replace(data, "\n", " ");
            data = StringUtils.normalizeSpace(data);
            return new SheetStringDataBean(data);
        }
        if(value instanceof Integer) {
            return new SheetIntegerDataBean((Integer) value);
        }
        if(value instanceof Boolean) {
            return new SheetBooleanDataBean((Boolean) value);
        }
        return null;
    }

}
