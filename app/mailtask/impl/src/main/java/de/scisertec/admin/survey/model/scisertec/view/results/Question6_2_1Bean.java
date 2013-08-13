package de.scisertec.admin.survey.model.scisertec.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Question6_2_1Bean implements Question6_2_1 {

    Map<String, Number> results = new LinkedHashMap<String, Number>();
    Set<Sheet> sheets;

    public Question6_2_1Bean(Set<Sheet> sheets) {
        this.sheets = sheets;
        collectResults();
    }

    @Override
    public Map<String, Number> getResults() {
        return results;
    }

    @Override
    public void collectResults() {
        Integer yes = 0;
        Integer no = 0;
        Integer dontknow = 0;
        for(Sheet sheet : sheets) {
            SheetData question6_2_1 = sheet.data().get("question6_2_1");
            if(question6_2_1 != null) {
                if(question6_2_1.data().equals(1)) {
                    yes++;
                }
                else if(question6_2_1.data().equals(1)) {
                    no++;
                }
                else {
                    dontknow++;
                }
            }
        }
        results.put("Ja", yes);
        results.put("Nein", no);
        results.put("Weiß ich nicht", dontknow);
    }
}
