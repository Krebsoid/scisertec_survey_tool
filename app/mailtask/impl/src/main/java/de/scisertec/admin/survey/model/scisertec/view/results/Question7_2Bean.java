package de.scisertec.admin.survey.model.scisertec.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Question7_2Bean implements Question7_2 {

    Map<String, Number> results = new LinkedHashMap<String, Number>();
    Set<Sheet> sheets;

    public Question7_2Bean(Set<Sheet> sheets) {
        this.sheets = sheets;
        collectResults();
    }

    @Override
    public Map<String, Number> getResults() {
        return results;
    }

    @Override
    public void collectResults() {
        Integer location = 0;
        Integer management = 0;
        Integer catering = 0;
        Integer technology = 0;
        for(Sheet sheet : sheets) {
            SheetData question7_2_a = sheet.data().get("question7_2_a");
            SheetData question7_2_b = sheet.data().get("question7_2_b");
            SheetData question7_2_c = sheet.data().get("question7_2_c");
            SheetData question7_2_d = sheet.data().get("question7_2_d");
            if(question7_2_a != null) {
                if(question7_2_a.data().equals(true)) {
                    location++;
                }
            }
            if(question7_2_b != null) {
                if(question7_2_b.data().equals(true)) {
                    management++;
                }
            }
            if(question7_2_c != null) {
                if(question7_2_c.data().equals(true)) {
                    catering++;
                }
            }
            if(question7_2_d != null) {
                if(question7_2_d.data().equals(true)) {
                    technology++;
                }
            }
        }
        results.put("Räumlichkeiten", location);
        results.put("Teilnehmermanagement", management);
        results.put("Catering", catering);
        results.put("Technik", technology);
    }
}
