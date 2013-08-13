package de.scisertec.admin.survey.model;

import de.scisertec.admin.mailtask.qualifier.MailTaskPersistence;
import de.scisertec.admin.survey.model.view.*;
import de.scisertec.admin.survey.service.SheetCatalog;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "survey")
public abstract class SurveyBean implements Survey {

    @Id
    @GeneratedValue
    protected Long id;

    @Enumerated
    protected SurveyStatus status = SurveyStatus.NOT_STARTED_YET;

    @Enumerated
    protected SurveyType surveyType;

    @Enumerated
    protected ConcreteSurveyType concreteSurveyType;

    protected DateTime creationDate = new DateTime();
    protected DateTime expirationDate = new DateTime();

    protected String name;

    @OneToMany(targetEntity = SheetBean.class)
    Set<Sheet> sheets = new HashSet<Sheet>();

    @Transient
    @Inject
    protected SheetCatalog sheetCatalog;

    @Transient
    @Inject
    @MailTaskPersistence
    EntityManager entityManager;

    @Transient
    protected final String linkAffix = "http://localhost:8080/survey/#/";

    @Override
    public Long id() {
        return id;
    }

    @Override
    public SurveyStatus status() {
        return status;
    }

    @Override
    public DateTime creationDate() {
        return creationDate;
    }

    @Override
    public DateTime expirationDate() {
        return expirationDate;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String linkAffix() {
        return linkAffix;
    }

    @Override
    public Set<Sheet> sheets() {
        return sheets;
    }

    @Override
    public SurveyType surveyType() {
        return surveyType;
    }

    @Override
    public ConcreteSurveyType concreteSurveyType() {
        return concreteSurveyType;
    }

    @Override
    public Survey name(String name) {
        this.name = name;
        return self();
    }

    @Override
    public Survey status(SurveyStatus status) {
        this.status = status;
        return self();
    }

    @Override
    public Survey surveyType(SurveyType surveyType) {
        this.surveyType = surveyType;
        return self();
    }

    @Override
    public Survey concreteSurveyType(ConcreteSurveyType concreteSurveyType) {
        this.concreteSurveyType = concreteSurveyType;
        return self();
    }

    @Override
    public Survey expirationDate(DateTime expirationDate) {
        this.expirationDate = expirationDate;
        return self();
    }

    @Override
    public Survey sheet(Sheet sheet) {
        this.sheets.add(sheet);
        return self();
    }

    @Override
    public Survey save() {
        entityManager.merge(self());
        return self();
    }

    public Survey self() {
        return this;
    }


    @Override
    public SurveySummary asSummary() {
        Integer filledSheets = 0;
        for(Sheet sheet : sheets()) {
            if(sheet.submissionDate() != null) {
                filledSheets++;
            }
        }
        return new SurveySummaryBean(id, name, surveyType, concreteSurveyType, status, sheets().size(), filledSheets, creationDate);
    }

    @Override
    public SurveyDetailed asDetailed() {
        return new SurveyDetailedBean(id, status, surveyType, linkAffix(), concreteSurveyType, name, creationDate, expirationDate, sheets);
    }

}
