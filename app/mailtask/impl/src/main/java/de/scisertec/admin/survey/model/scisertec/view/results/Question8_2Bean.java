package de.scisertec.admin.survey.model.scisertec.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Question8_2Bean implements Question8_2 {

    Map<String, Number> results = new LinkedHashMap<String, Number>();
    Set<Sheet> sheets;

    public Question8_2Bean(Set<Sheet> sheets) {
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
        Integer dontKnow = 0;
        for(Sheet sheet : sheets) {
            SheetData question8_2 = sheet.data().get("question8_2");
            if(question8_2 != null) {
                if(question8_2.data().equals(1)) {
                    yes++;
                }
                else if(question8_2.data().equals(2)) {
                    no++;
                }
                else {
                    dontKnow++;
                }
            }
        }
        results.put("Ja", yes);
        results.put("Nein", no);
        results.put("Weiß ich nicht", dontKnow);
    }
}
