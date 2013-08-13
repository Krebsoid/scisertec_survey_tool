package de.scisertec.admin.survey.model.vcongress.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class RoleQuestionBean implements RoleQuestion {

    Map<String, Number> results = new LinkedHashMap<String, Number>();
    Set<Sheet> sheets;

    public RoleQuestionBean(Set<Sheet> sheets) {
        this.sheets = sheets;
        collectResults();
    }

    @Override
    public Map<String, Number> getResults() {
        return results;
    }

    @Override
    public void collectResults() {
        Integer participant = 0;
        Integer organizer = 0;
        Integer reviewer = 0;
        for(Sheet sheet : sheets) {
            SheetData role = sheet.data().get("role");
            if(role != null) {
                if(role.data().equals("PARTICIPANT")) {
                    participant++;
                }
                if(role.data().equals("ORGANIZER")) {
                    organizer++;
                }
                if(role.data().equals("REVIEWER")) {
                    reviewer++;
                }
            }
        }
        results.put("Participant", participant);
        results.put("Organizer", organizer);
        results.put("Reviewer", reviewer);
    }
}
