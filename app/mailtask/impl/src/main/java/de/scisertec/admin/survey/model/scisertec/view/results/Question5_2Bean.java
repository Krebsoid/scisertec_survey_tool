package de.scisertec.admin.survey.model.scisertec.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Question5_2Bean implements Question5_2 {

    Map<String, Number> results = new LinkedHashMap<String, Number>();
    Set<Sheet> sheets;

    public Question5_2Bean(Set<Sheet> sheets) {
        this.sheets = sheets;
        collectResults();
    }

    @Override
    public Map<String, Number> getResults() {
        return results;
    }

    @Override
    public void collectResults() {
        Integer collegues = 0;
        Integer library = 0;
        Integer internet = 0;
        Integer further = 0;
        for(Sheet sheet : sheets) {
            SheetData question5_2_a = sheet.data().get("question5_2_a");
            SheetData question5_2_b = sheet.data().get("question5_2_b");
            SheetData question5_2_c = sheet.data().get("question5_2_c");
            SheetData question5_2_d = sheet.data().get("question5_2_d");
            if(question5_2_a != null) {
                if(question5_2_a.data().equals(true)) {
                    collegues++;
                }
            }
            if(question5_2_b != null) {
                if(question5_2_b.data().equals(true)) {
                    library++;
                }
            }
            if(question5_2_c != null) {
                if(question5_2_c.data().equals(true)) {
                    internet++;
                }
            }
            if(question5_2_d != null) {
                if(question5_2_d.data().equals(true)) {
                    further++;
                }
            }
        }
        results.put("Kollegen", collegues);
        results.put("Bibliothek", library);
        results.put("Internet", internet);
        results.put("Weiteres", further);
    }
}
