package de.scisertec.admin.survey.model.vcongress;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SurveyBean;
import de.scisertec.admin.survey.model.scisertec.view.SciSerTecSheetCsvHolderBean;
import de.scisertec.admin.survey.model.vcongress.view.results.vCongressSurveyResultsBean;
import de.scisertec.admin.survey.model.vcongress.view.vCongressSheetCsvHolderBean;
import de.scisertec.admin.survey.model.vcongress.view.vCongressSurveyDetailed;
import de.scisertec.admin.survey.model.vcongress.view.vCongressSurveyDetailedBean;
import de.scisertec.admin.survey.model.view.SurveyResults;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class vCongressSurveyBean extends SurveyBean implements vCongressSurvey {

    Boolean payment = false;
    Boolean abstractSubmission = false;
    Boolean reviewing = false;

    @Override
    public String linkAffix() {
        return super.linkAffix()+"vcongress/" + id + "/welcome";
    }

    @Override
    public vCongressSurvey self() {
        return this;
    }

    @Override
    public SurveyResults asResults() {
        return new vCongressSurveyResultsBean(sheets());
    }

    @Override
    public String asCsv() {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(vCongressSheetCsvHolderBean.class)
                .withHeader().withColumnSeparator(';')
                .withLineSeparator("\n");
        List<vCongressSheetCsvHolderBean> entries = new ArrayList<vCongressSheetCsvHolderBean>();
        for(Sheet sheet : sheets()) {
            vCongressSheetCsvHolderBean holderBean = new vCongressSheetCsvHolderBean(sheet);
            entries.add(holderBean);
        }
        String csv;
        try {
            csv = mapper.writer(schema).writeValueAsString(entries);
        } catch (JsonProcessingException e) {
            return "";
        }
        return csv;
    }

    @Override
    public Boolean payment() {
        return payment;
    }

    @Override
    public Boolean abstractSubmission() {
        return abstractSubmission;
    }

    @Override
    public Boolean reviewing() {
        return reviewing;
    }

    @Override
    public vCongressSurvey payment(Boolean payment) {
        this.payment = payment;
        return this;
    }

    @Override
    public vCongressSurvey abstractSubmission(Boolean abstractSubmission) {
        this.abstractSubmission = abstractSubmission;
        return this;
    }

    @Override
    public vCongressSurvey reviewing(Boolean reviewing) {
        this.reviewing = reviewing;
        return this;
    }

    @Override
    public vCongressSurveyDetailed asDetailed() {
        return new vCongressSurveyDetailedBean(id, status, surveyType, linkAffix(), concreteSurveyType, name, creationDate,
                expirationDate, sheets(), payment, abstractSubmission, reviewing);
    }
}
