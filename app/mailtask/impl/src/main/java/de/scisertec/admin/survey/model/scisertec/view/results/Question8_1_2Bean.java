package de.scisertec.admin.survey.model.scisertec.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Question8_1_2Bean implements Question8_1_2 {

    Map<String, Number> results = new LinkedHashMap<String, Number>();
    Set<Sheet> sheets;

    public Question8_1_2Bean(Set<Sheet> sheets) {
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
            SheetData question8_1_2 = sheet.data().get("question8_1_2");
            if(question8_1_2 != null) {
                if(question8_1_2.data().equals(1)) {
                    oneMan++;
                }
                else if(question8_1_2.data().equals(2)) {
                    severalWorker++;
                }
                else if(question8_1_2.data().equals(3)) {
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
