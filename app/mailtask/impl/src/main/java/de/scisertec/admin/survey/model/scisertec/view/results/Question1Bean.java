package de.scisertec.admin.survey.model.scisertec.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Question1Bean implements Question3_1 {

    Map<String, Number> results = new LinkedHashMap<String, Number>();
    Set<Sheet> sheets;

    public Question1Bean(Set<Sheet> sheets) {
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
        for(Sheet sheet : sheets) {
            SheetData question1 = sheet.data().get("question1");
            if(question1 != null) {
                if(question1.data().equals(true)) {
                    yes++;
                }
                else {
                    no++;
                }
            }
        }
        results.put("Ja", yes);
        results.put("Nein", no);
    }
}
