package de.scisertec.admin.survey.model.vcongress.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class PaymentRatingBean implements PaymentRating {

    Map<String, Number> results = new LinkedHashMap<String, Number>();
    Set<Sheet> sheets;

    public PaymentRatingBean(Set<Sheet> sheets) {
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
            SheetData payment = sheet.data().get("payment");
            if(payment != null) {
                if(payment.data().equals(1)) {
                    one++;
                }
                if(payment.data().equals(2)) {
                    two++;
                }
                if(payment.data().equals(3)) {
                    three++;
                }
                if(payment.data().equals(4)) {
                    four++;
                }
                if(payment.data().equals(5)) {
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
