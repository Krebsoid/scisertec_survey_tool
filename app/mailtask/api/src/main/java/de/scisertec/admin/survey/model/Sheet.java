package de.scisertec.admin.survey.model;

import de.scisertec.admin.core.model.TechnicalId;
import de.scisertec.admin.survey.model.view.SheetContent;
import org.joda.time.DateTime;

import java.util.Map;

public interface Sheet extends TechnicalId {

    DateTime submissionDate();
    Map<String, SheetData> data();
    String uuid();

    Sheet data(Map<String, Object> data);
    Sheet data(String key, Object value);
    Sheet submissionDate(DateTime submissionDate);

    Sheet save();

    SheetContent asContent();

}
