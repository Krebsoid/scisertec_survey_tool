package de.scisertec.admin.survey.model.scisertec.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Question5_2_cBean implements Question5_2_c {

    Map<String, Number> results = new LinkedHashMap<String, Number>();
    Set<Sheet> sheets;

    public Question5_2_cBean(Set<Sheet> sheets) {
        this.sheets = sheets;
        collectResults();
    }

    @Override
    public Map<String, Number> getResults() {
        return results;
    }

    @Override
    public void collectResults() {
        Integer google = 0;
        Integer bing = 0;
        Integer ask = 0;
        Integer blogs = 0;
        Integer chats = 0;
        Integer catalogs = 0;
        for(Sheet sheet : sheets) {
            SheetData question5_2_c_a = sheet.data().get("question5_2_c_a");
            SheetData question5_2_c_b = sheet.data().get("question5_2_c_b");
            SheetData question5_2_c_c = sheet.data().get("question5_2_c_c");
            SheetData question5_2_c_d = sheet.data().get("question5_2_c_d");
            SheetData question5_2_c_e = sheet.data().get("question5_2_c_e");
            SheetData question5_2_c_f = sheet.data().get("question5_2_c_f");
            if(question5_2_c_a != null) {
                if(question5_2_c_a.data().equals(true)) {
                    google++;
                }
            }
            if(question5_2_c_b != null) {
                if(question5_2_c_b.data().equals(true)) {
                    bing++;
                }
            }
            if(question5_2_c_c != null) {
                if(question5_2_c_c.data().equals(true)) {
                    ask++;
                }
            }
            if(question5_2_c_d != null) {
                if(question5_2_c_d.data().equals(true)) {
                    blogs++;
                }
            }
            if(question5_2_c_e != null) {
                if(question5_2_c_e.data().equals(true)) {
                    chats++;
                }
            }
            if(question5_2_c_f != null) {
                if(question5_2_c_f.data().equals(true)) {
                    catalogs++;
                }
            }
        }
        results.put("Google", google);
        results.put("Bing", bing);
        results.put("Ask", ask);
        results.put("Blogs", blogs);
        results.put("Chats", chats);
        results.put("Verzeichnisse", catalogs);
    }
}
