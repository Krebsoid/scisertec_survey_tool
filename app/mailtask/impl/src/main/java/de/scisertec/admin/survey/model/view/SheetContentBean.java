package de.scisertec.admin.survey.model.view;

import de.scisertec.admin.survey.model.SheetData;
import de.scisertec.admin.survey.model.SheetDataBean;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

public class SheetContentBean implements SheetContent {

    Long id;

    DateTime submissionDate;
    String uuid;

    Map<String, Object> data = new HashMap<String, Object>();

    public SheetContentBean(Long id, DateTime submissionDate, String uuid, Map<String, SheetData> data) {
        for(Map.Entry<String, SheetData> entry : data.entrySet()) {
            this.data.put(entry.getKey(), entry.getValue().data());
        }
        this.id = id;
        this.uuid = uuid;
        this.submissionDate = submissionDate;
    }


    @Override
    public DateTime getSubmissionDate() {
        return submissionDate;
    }

    @Override
    public Map<String, Object> getData() {
        return data;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getUuid() {
        return uuid;
    }
}
