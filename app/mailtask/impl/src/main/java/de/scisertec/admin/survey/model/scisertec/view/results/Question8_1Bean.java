package de.scisertec.admin.survey.model.scisertec.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Question8_1Bean implements Question8_1 {

    Map<String, Number> results = new LinkedHashMap<String, Number>();
    Set<Sheet> sheets;

    public Question8_1Bean(Set<Sheet> sheets) {
        this.sheets = sheets;
        collectResults();
    }

    @Override
    public Map<String, Number> getResults() {
        return results;
    }

    @Override
    public void collectResults() {
        Integer allInclusive = 0;
        Integer perParticipant = 0;
        Integer both = 0;
        Integer different = 0;
        for(Sheet sheet : sheets) {
            SheetData question8_1 = sheet.data().get("question8_1");
            if(question8_1 != null) {
                if(question8_1.data().equals(1)) {
                    allInclusive++;
                }
                else if(question8_1.data().equals(2)) {
                    perParticipant++;
                }
                else if(question8_1.data().equals(3)) {
                    both++;
                }
                else {
                    different++;
                }
            }
        }
        results.put("Pauschale", allInclusive);
        results.put("pro Teilnehmer", perParticipant);
        results.put("beides", both);
        results.put("Andere", different);
    }
}
