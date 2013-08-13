package de.scisertec.admin.survey.model.scisertec.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Question5_1_1Bean implements Question5_1_1 {

    Map<String, Number> results = new LinkedHashMap<String, Number>();
    Set<Sheet> sheets;

    public Question5_1_1Bean(Set<Sheet> sheets) {
        this.sheets = sheets;
        collectResults();
    }

    @Override
    public Map<String, Number> getResults() {
        return results;
    }

    @Override
    public void collectResults() {
        Integer ltFiveThousand = 0;
        Integer fiveToTenThousand = 0;
        Integer gtTenThousand = 0;
        Integer different = 0;
        for(Sheet sheet : sheets) {
            SheetData question5_1_1 = sheet.data().get("question5_1_1");
            if(question5_1_1 != null) {
                if(question5_1_1.data().equals(1)) {
                    ltFiveThousand++;
                }
                else if(question5_1_1.data().equals(2)) {
                    fiveToTenThousand++;
                }
                else if(question5_1_1.data().equals(3)) {
                    gtTenThousand++;
                }
                else {
                    different++;
                }
            }
        }
        results.put("&lt; 5.000 Euro ", ltFiveThousand);
        results.put("5.000 - 10.000 Euro ", fiveToTenThousand);
        results.put("&gt; 10.000 Euro ", gtTenThousand);
        results.put("Andere", different);
    }
}
