package de.scisertec.admin.survey.model.scisertec.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Question3_2Bean implements Question3_2 {

    Map<String, Number> results = new LinkedHashMap<String, Number>();
    Set<Sheet> sheets;

    public Question3_2Bean(Set<Sheet> sheets) {
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
            SheetData question3_2 = sheet.data().get("question3_2");
            if(question3_2 != null) {
                if(question3_2.data().equals(1)) {
                    yes++;
                }
                else if(question3_2.data().equals(1)) {
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
