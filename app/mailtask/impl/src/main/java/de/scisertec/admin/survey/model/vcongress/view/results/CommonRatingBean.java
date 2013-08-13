package de.scisertec.admin.survey.model.vcongress.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class CommonRatingBean implements CommonRating {

    Map<String, Number> results = new LinkedHashMap<String, Number>();
    Set<Sheet> sheets;

    public CommonRatingBean(Set<Sheet> sheets) {
        this.sheets = sheets;
        collectResults();
    }

    @Override
    public Map<String, Number> getResults() {
        return results;
    }

    @Override
    public void collectResults() {
        Integer one = 0;
        Integer two = 0;
        Integer three = 0;
        Integer four = 0;
        Integer five = 0;
        for(Sheet sheet : sheets) {
            SheetData common = sheet.data().get("common");
            if(common != null) {
                if(common.data().equals(1)) {
                    one++;
                }
                if(common.data().equals(2)) {
                    two++;
                }
                if(common.data().equals(3)) {
                    three++;
                }
                if(common.data().equals(4)) {
                    four++;
                }
                if(common.data().equals(5)) {
                    five++;
                }
            }
        }
        results.put("1", one);
        results.put("2", two);
        results.put("3", three);
        results.put("4", four);
        results.put("5", five);
    }
}
