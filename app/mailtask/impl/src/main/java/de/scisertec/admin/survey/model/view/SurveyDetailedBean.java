package de.scisertec.admin.survey.model.view;

import de.scisertec.admin.survey.model.ConcreteSurveyType;
import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SurveyStatus;
import de.scisertec.admin.survey.model.SurveyType;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SurveyDetailedBean implements SurveyDetailed {

    Long id;

    SurveyStatus status;
    String link;
    SurveyType surveyType;
    ConcreteSurveyType concreteSurveyType;

    String name;
    DateTime creationDate;
    DateTime expirationDate;

    Set<SheetContentBean> sheets = new HashSet<SheetContentBean>();


    public SurveyDetailedBean(Long id, SurveyStatus status, SurveyType surveyType, String link, ConcreteSurveyType concreteSurveyType,
                              String name, DateTime creationDate, DateTime expirationDate, Set<Sheet> sheets) {
        this.id = id;
        this.status = status;
        this.surveyType = surveyType;
        this.link = link;
        this.concreteSurveyType = concreteSurveyType;
        this.name = name;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        for(Sheet sheet : sheets) {
            SheetContentBean sheetContent = (SheetContentBean) sheet.asContent();
            this.sheets.add(sheetContent);
        }
    }

    @Override
    public SurveyStatus getStatus() {
        return status;
    }

    @Override
    public String getLink() {
        return link;
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
    public DateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public DateTime getExpirationDate() {
        return expirationDate;
    }

    @Override
    public Long getTimeRemaining() {
        return expirationDate.getMillis() - DateTime.now().getMillis();
    }

    @Override
    public List<SheetContent> getSheets() {
        return new ArrayList<SheetContent>(sheets);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Long getId() {
        return id;
    }

}
