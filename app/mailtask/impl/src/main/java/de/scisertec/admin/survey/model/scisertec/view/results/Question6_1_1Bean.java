package de.scisertec.admin.survey.model.scisertec.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Question6_1_1Bean implements Question6_1_1 {

    Map<String, Number> results = new LinkedHashMap<String, Number>();
    Set<Sheet> sheets;

    public Question6_1_1Bean(Set<Sheet> sheets) {
        this.sheets = sheets;
        collectResults();
    }

    @Override
    public Map<String, Number> getResults() {
        return results;
    }

    @Override
    public void collectResults() {
        Integer oneMan = 0;
        Integer severalWorker = 0;
        Integer fullServiceUnit = 0;
        Integer different = 0;
        for(Sheet sheet : sheets) {
            SheetData question6_1_1 = sheet.data().get("question6_1_1");
            if(question6_1_1 != null) {
                if(question6_1_1.data().equals(1)) {
                    oneMan++;
                }
                else if(question6_1_1.data().equals(2)) {
                    severalWorker++;
                }
                else if(question6_1_1.data().equals(3)) {
                    fullServiceUnit++;
                }
                else {
                    different++;
                }
            }
        }
        results.put("Ein-Mann-Betrieb", oneMan);
        results.put("mehrere Mitarbeiter", severalWorker);
        results.put("Full Service Unit", fullServiceUnit);
        results.put("Andere", different);
    }
}
