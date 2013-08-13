package de.scisertec.admin.survey.model.scisertec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import de.scisertec.admin.account.model.Group;
import de.scisertec.admin.account.service.GroupCatalog;
import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SurveyBean;
import de.scisertec.admin.survey.model.scisertec.view.SciSerTecSheetCsvHolderBean;
import de.scisertec.admin.survey.model.scisertec.view.results.SciSerTecSurveyResultsBean;
import de.scisertec.admin.survey.model.view.SurveyResults;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SciSerTecSurveyBean extends SurveyBean implements SciSerTecSurvey {

    Long groupId;

    @Transient
    @Inject
    GroupCatalog groupCatalog;

    @Override
    public String linkAffix() {
        return super.linkAffix()+"scisertec/" + id + "/welcome";
    }

    @Override
    public SciSerTecSurvey self() {
        return this;
    }

    @Override
    public SurveyResults asResults() {
        return new SciSerTecSurveyResultsBean(sheets());
    }

    @Override
    public String asCsv() {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(SciSerTecSheetCsvHolderBean.class)
                .withHeader().withColumnSeparator(';')
                .withLineSeparator("\n");
        List<SciSerTecSheetCsvHolderBean> entries = new ArrayList<SciSerTecSheetCsvHolderBean>();
        for(Sheet sheet : sheets()) {
            SciSerTecSheetCsvHolderBean holderBean = new SciSerTecSheetCsvHolderBean(sheet);
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
    public Group group() {
        return groupCatalog.findById(groupId);
    }

    @Override
    public SciSerTecSurvey group(Group group) {
        this.groupId = group.id();
        return self();
    }
}
