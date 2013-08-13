package de.scisertec.admin.survey.model.scisertec.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Question2Bean implements Question2 {

    Map<String, Number> results = new LinkedHashMap<String, Number>();
    Set<Sheet> sheets;

    public Question2Bean(Set<Sheet> sheets) {
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
            SheetData question2 = sheet.data().get("question2");
            if(question2 != null) {
                if(question2.data().equals(true)) {
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
