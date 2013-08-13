package de.scisertec.admin.survey.model.scisertec.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Question4_2Bean implements Question4_2 {

    Map<String, Number> results = new LinkedHashMap<String, Number>();
    Set<Sheet> sheets;

    public Question4_2Bean(Set<Sheet> sheets) {
        this.sheets = sheets;
        collectResults();
    }

    @Override
    public Map<String, Number> getResults() {
        return results;
    }

    @Override
    public void collectResults() {
        Integer conversation = 0;
        Integer poster = 0;
        Integer catering = 0;
        Integer location = 0;
        for(Sheet sheet : sheets) {
            SheetData question4_2_a = sheet.data().get("question4_2_a");
            SheetData question4_2_b = sheet.data().get("question4_2_b");
            SheetData question4_2_c = sheet.data().get("question4_2_c");
            SheetData question4_2_d = sheet.data().get("question4_2_d");
            if(question4_2_a != null) {
                if(question4_2_a.data().equals(true)) {
                    conversation++;
                }
            }
            if(question4_2_b != null) {
                if(question4_2_b.data().equals(true)) {
                    poster++;
                }
            }
            if(question4_2_c != null) {
                if(question4_2_c.data().equals(true)) {
                    catering++;
                }
            }
            if(question4_2_d != null) {
                if(question4_2_d.data().equals(true)) {
                    location++;
                }
            }
        }
        results.put("Austausch mit Kollegen", conversation);
        results.put("Poster und Vorträge", poster);
        results.put("Catering", catering);
        results.put("Location", location);
    }
}
