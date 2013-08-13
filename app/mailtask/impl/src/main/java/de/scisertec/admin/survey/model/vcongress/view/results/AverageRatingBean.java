package de.scisertec.admin.survey.model.vcongress.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class AverageRatingBean implements AverageRating {

    Map<String, Number> results = new LinkedHashMap<String, Number>();
    Set<Sheet> sheets;

    public AverageRatingBean(Set<Sheet> sheets) {
        this.sheets = sheets;
        collectResults();
    }

    @Override
    public Map<String, Number> getResults() {
        return results;
    }

    @Override
    public void collectResults() {
        Integer common = 0;
        Integer payment = 0;
        Integer paper = 0;
        Integer support = 0;
        Integer website = 0;

        Integer commonVoting = 0;
        Integer paymentVoting = 0;
        Integer paperVoting = 0;
        Integer supportVoting = 0;
        Integer websiteVoting = 0;
        for(Sheet sheet : sheets) {
            SheetData commonData = sheet.data().get("common");
            SheetData paymentData = sheet.data().get("payment");
            SheetData paperData = sheet.data().get("paper");
            SheetData supportData = sheet.data().get("support");
            SheetData websiteData = sheet.data().get("website");
            if(commonData != null) {
                Integer data = (Integer) commonData.data();
                if(data > 0) {
                    common += data;
                    commonVoting++;
                }
            }
            if(paymentData != null) {
                Integer data = (Integer) paymentData.data();
                if(data > 0) {
                    payment += data;
                    paymentVoting++;
                }
            }
            if(paperData != null) {
                Integer data = (Integer) paperData.data();
                if(data > 0) {
                    paper += data;
                    paperVoting++;
                }
            }
            if(supportData != null) {
                Integer data = (Integer) supportData.data();
                if(data > 0) {
                    support += data;
                    supportVoting++;
                }
            }
            if(websiteData != null) {
                Integer data = (Integer) websiteData.data();
                if(data > 0) {
                    website += data;
                    websiteVoting++;
                }
            }
        }

        Float commonAverage = common.floatValue() / commonVoting.floatValue();
        Float paymentAverage = payment.floatValue() / paymentVoting.floatValue();
        Float paperAverage = paper.floatValue() / paperVoting.floatValue();
        Float supportAverage = support.floatValue() / supportVoting.floatValue();
        Float websiteAverage = website.floatValue() / websiteVoting.floatValue();

        results.put("Common", commonAverage);
        results.put("Payment", paymentAverage);
        results.put("Paper", paperAverage);
        results.put("Support", supportAverage);
        results.put("Website", websiteAverage);
    }
}
