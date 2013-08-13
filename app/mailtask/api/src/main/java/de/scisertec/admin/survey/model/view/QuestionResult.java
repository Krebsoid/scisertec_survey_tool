package de.scisertec.admin.survey.model.view;

import java.util.Map;

public interface QuestionResult {

    Map<String, Number> getResults();

    void collectResults();

}
