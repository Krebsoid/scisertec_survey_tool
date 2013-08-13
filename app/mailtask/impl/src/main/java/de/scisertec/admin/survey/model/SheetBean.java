package de.scisertec.admin.survey.model;

import de.scisertec.admin.mailtask.qualifier.MailTaskPersistence;
import de.scisertec.admin.survey.model.view.SheetContent;
import de.scisertec.admin.survey.model.view.SheetContentBean;
import de.scisertec.admin.survey.service.SheetDataFactoryBean;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.persistence.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "sheet")
public class SheetBean implements Sheet {

    @Id
    @GeneratedValue
    protected Long id;

    protected DateTime submissionDate;

    @OneToMany(cascade = CascadeType.ALL)
    Map<String, SheetDataBean> data = new LinkedHashMap<String, SheetDataBean>();

    String uuid;


    @Transient
    @Inject
    @MailTaskPersistence
    EntityManager entityManager;

    @Transient
    @Inject
    SheetDataFactoryBean sheetDataFactoryBean;


    public SheetBean() {
        generateUuid();
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public DateTime submissionDate() {
        return submissionDate;
    }

    @Override
    public Map<String, SheetData> data() {
        return new HashMap<String, SheetData>(data);
    }

    @Override
    public String uuid() {
        return uuid;
    }

    @Override
    public Sheet data(Map<String, Object> data) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            this.data(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override
    public Sheet data(String key, Object value) {
        SheetData sheetData = sheetDataFactoryBean.create(value);
        if(this.data.containsKey(key)) {
            SheetDataBean oldData = this.data.get(key);
            this.data.remove(key);
            entityManager.remove(oldData);
        }
        this.data.put(key, (SheetDataBean) sheetData);
        return this;
    }

    @Override
    public Sheet submissionDate(DateTime submissionDate) {
        this.submissionDate = submissionDate;
        return self();
    }

    @Override
    public Sheet save() {
        entityManager.merge(self());
        return self();
    }

    @Override
    public SheetContent asContent() {
        return new SheetContentBean(id, submissionDate, uuid, data());
    }


    public Sheet self() {
        return this;
    }


    protected Sheet generateUuid() {
        this.uuid = UUID.randomUUID().toString();
        return self();
    }

}
