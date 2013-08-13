package de.scisertec.admin.survey.model.scisertec.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Question5_1Bean implements Question5_1 {

    Map<String, Number> results = new LinkedHashMap<String, Number>();
    Set<Sheet> sheets;

    public Question5_1Bean(Set<Sheet> sheets) {
        this.sheets = sheets;
        collectResults();
    }

    @Override
    public Map<String, Number> getResults() {
        return results;
    }

    @Override
    public void collectResults() {
        Integer self = 0;
        Integer partlySelf = 0;
        Integer notSelf = 0;
        Integer different = 0;
        for(Sheet sheet : sheets) {
            SheetData question5_1 = sheet.data().get("question5_1");
            if(question5_1 != null) {
                if(question5_1.data().equals(1)) {
                    self++;
                }
                else if(question5_1.data().equals(2)) {
                    partlySelf++;
                }
                else if(question5_1.data().equals(3)) {
                    notSelf++;
                }
                else {
                    different++;
                }
            }
        }
        results.put("selbst organisieren", self);
        results.put("teilweise selbst organisieren", partlySelf);
        results.put("alles als Dienstleistung organisieren lassen", notSelf);
        results.put("Andere", different);
    }
}
