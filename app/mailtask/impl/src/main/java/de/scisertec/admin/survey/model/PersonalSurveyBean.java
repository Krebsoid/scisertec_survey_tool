package de.scisertec.admin.survey.model;

import de.scisertec.admin.mailtask.model.MailJob;

import javax.persistence.Entity;
import java.util.LinkedList;
import java.util.Queue;

@Entity
public abstract class PersonalSurveyBean extends MailTaskSurveyBean implements PersonalSurvey {

    @Override
    public PersonalSurvey createSheets() {
        assert(mailTask() != null);
        Queue<MailJob> mailJobs = new LinkedList<MailJob>(mailTask().mailJobs());
        int size = mailJobs.size();
        for(int i = 0 ; i < size ; i++) {
            PersonalSheetBean sheet = (PersonalSheetBean) this.sheetCatalog.create(ConcreteSurveyType.PERSONAL);
            sheet.mailReceiver(mailJobs.poll().receiver()).save();
            this.sheets.add(sheet);
        }
        return self();
    }

    public PersonalSurvey self() {
        return this;
    }

}
