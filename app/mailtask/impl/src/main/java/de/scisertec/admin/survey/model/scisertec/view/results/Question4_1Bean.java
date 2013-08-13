package de.scisertec.admin.survey.model.scisertec.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Question4_1Bean implements Question4_1 {

    Map<String, Number> results = new LinkedHashMap<String, Number>();
    Set<Sheet> sheets;

    public Question4_1Bean(Set<Sheet> sheets) {
        this.sheets = sheets;
        collectResults();
    }

    @Override
    public Map<String, Number> getResults() {
        return results;
    }

    @Override
    public void collectResults() {
        Integer ltSixMoths = 0;
        Integer sixToTwelveMonths = 0;
        Integer gtTwelveMonths = 0;
        Integer different = 0;
        for(Sheet sheet : sheets) {
            SheetData question4_1 = sheet.data().get("question4_1");
            if(question4_1 != null) {
                if(question4_1.data().equals(1)) {
                    ltSixMoths++;
                }
                else if(question4_1.data().equals(2)) {
                    sixToTwelveMonths++;
                }
                else if(question4_1.data().equals(3)) {
                    gtTwelveMonths++;
                }
                else {
                    different++;
                }
            }
        }
        results.put("&lt; 6 Monate", ltSixMoths);
        results.put("6 - 12 Monate", sixToTwelveMonths);
        results.put("&gt; 12 Monate", gtTwelveMonths);
        results.put("Andere", different);
    }
}
