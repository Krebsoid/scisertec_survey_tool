package de.scisertec.admin.survey.model.scisertec.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Question9_2Bean implements Question9_2 {

    Map<String, Number> results = new LinkedHashMap<String, Number>();
    Set<Sheet> sheets;

    public Question9_2Bean(Set<Sheet> sheets) {
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
            SheetData question9_2_a = sheet.data().get("question9_2_a");
            SheetData question9_2_b = sheet.data().get("question9_2_b");
            SheetData question9_2_c = sheet.data().get("question9_2_c");
            SheetData question9_2_d = sheet.data().get("question9_2_d");
            if(question9_2_a != null) {
                if(question9_2_a.data().equals(true)) {
                    reg++;
                }
            }
            if(question9_2_b != null) {
                if(question9_2_b.data().equals(true)) {
                    payment++;
                }
            }
            if(question9_2_c != null) {
                if(question9_2_c.data().equals(true)) {
                    paper++;
                }
            }
            if(question9_2_d != null) {
                if(question9_2_d.data().equals(true)) {
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
