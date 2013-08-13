package de.scisertec.admin.survey.model.scisertec.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Question7_1Bean implements Question7_1 {

    Map<String, Number> results = new LinkedHashMap<String, Number>();
    Set<Sheet> sheets;

    public Question7_1Bean(Set<Sheet> sheets) {
        this.sheets = sheets;
        collectResults();
    }

    @Override
    public Map<String, Number> getResults() {
        return results;
    }

    @Override
    public void collectResults() {
        Integer reg = 0;
        Integer payment = 0;
        Integer paper = 0;
        Integer review = 0;
        for(Sheet sheet : sheets) {
            SheetData question7_1_a = sheet.data().get("question7_1_a");
            SheetData question7_1_b = sheet.data().get("question7_1_b");
            SheetData question7_1_c = sheet.data().get("question7_1_c");
            SheetData question7_1_d = sheet.data().get("question7_1_d");
            if(question7_1_a != null) {
                if(question7_1_a.data().equals(true)) {
                    reg++;
                }
            }
            if(question7_1_b != null) {
                if(question7_1_b.data().equals(true)) {
                    payment++;
                }
            }
            if(question7_1_c != null) {
                if(question7_1_c.data().equals(true)) {
                    paper++;
                }
            }
            if(question7_1_d != null) {
                if(question7_1_d.data().equals(true)) {
                    review++;
                }
            }
        }
        results.put("Registrierung", reg);
        results.put("Bezahlung", payment);
        results.put("Abstracts", paper);
        results.put("Review", review);
    }
}
