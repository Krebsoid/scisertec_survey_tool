package de.scisertec.admin.survey.model.view;

import de.scisertec.admin.core.model.view.ViewId;
import org.joda.time.DateTime;

import java.util.Map;

public interface SheetContent extends ViewId {

    DateTime getSubmissionDate();
    String getUuid();
    Map<String, Object> getData();

}
