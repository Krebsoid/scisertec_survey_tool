package de.scisertec.admin.survey.model.scisertec.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Question9_1Bean implements Question9_1 {

    Map<String, Number> results = new LinkedHashMap<String, Number>();
    Set<Sheet> sheets;

    public Question9_1Bean(Set<Sheet> sheets) {
        this.sheets = sheets;
        collectResults();
    }

    @Override
    public Map<String, Number> getResults() {
        return results;
    }

    @Override
    public void collectResults() {
        Integer allInclusiveLtFiveThousand = 0;
        Integer allInclusiveFiveToTenThousand = 0;
        Integer allInclusiveGtTenThousand = 0;
        Integer perParticipantLtTen = 0;
        Integer perParticipantTenToThirty = 0;
        Integer perParticipantGtThirty = 0;
        Integer different = 0;
        for(Sheet sheet : sheets) {
            SheetData question9_1 = sheet.data().get("question9_1");
            if(question9_1 != null) {
                if(question9_1.data().equals(1)) {
                    allInclusiveLtFiveThousand++;
                }
                else if(question9_1.data().equals(2)) {
                    allInclusiveFiveToTenThousand++;
                }
                else if(question9_1.data().equals(3)) {
                    allInclusiveGtTenThousand++;
                }
                else if(question9_1.data().equals(4)) {
                    perParticipantLtTen++;
                }
                else if(question9_1.data().equals(5)) {
                    perParticipantTenToThirty++;
                }
                else if(question9_1.data().equals(6)) {
                    perParticipantGtThirty++;
                }
                else {
                    different++;
                }
            }
        }
        results.put("Pauschale &lt; 5.000 Euro ", allInclusiveLtFiveThousand);
        results.put("Pauschale 5.000 - 10.000 Euro ", allInclusiveFiveToTenThousand);
        results.put("Pauschale &gt; 10.000 Euro ", allInclusiveGtTenThousand);
        results.put("pro Teilnehmer &lt; 10 Euro", perParticipantLtTen);
        results.put("pro Teilnehmer 10 - 30 Euro", perParticipantTenToThirty);
        results.put("pro Teilnehmer &gt; 30 Euro", perParticipantGtThirty);
        results.put("Andere", different);
    }
}
