package de.scisertec.admin.survey.model.view;

import de.scisertec.admin.survey.model.ConcreteSurveyType;
import de.scisertec.admin.survey.model.SurveyStatus;
import de.scisertec.admin.survey.model.SurveyType;
import org.joda.time.DateTime;

public class SurveySummaryBean implements SurveySummary {

    Long id;
    String name;

    SurveyType surveyType;
    ConcreteSurveyType concreteSurveyType;
    SurveyStatus status;

    Integer sheetCount;
    Integer filledSheetCount;

    DateTime creationDate;

    public SurveySummaryBean(Long id, String name, SurveyType surveyType, ConcreteSurveyType concreteSurveyType,
                             SurveyStatus status, Integer sheetCount, Integer filledSheetCount, DateTime creationDate) {
        this.id = id;
        this.name = name;
        this.surveyType = surveyType;
        this.concreteSurveyType = concreteSurveyType;
        this.status = status;
        this.sheetCount = sheetCount;
        this.filledSheetCount = filledSheetCount;
        this.creationDate = creationDate;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public SurveyStatus getStatus() {
        return status;
    }

    @Override
    public SurveyType getSurveyType() {
        return surveyType;
    }

    @Override
    public ConcreteSurveyType getConcreteSurveyType() {
        return concreteSurveyType;
    }

    @Override
    public Integer getSheetCount() {
        return sheetCount;
    }

    @Override
    public Integer getFilledSheetCount() {
        return filledSheetCount;
    }

    @Override
    public DateTime getCreationDate() {
        return creationDate;
    }
}
