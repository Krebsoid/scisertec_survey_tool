package de.scisertec.admin.survey.model.view;


import de.scisertec.admin.mailtask.model.view.MailReceiverContent;
import de.scisertec.admin.survey.model.SheetData;
import de.scisertec.admin.survey.model.SheetDataBean;
import org.joda.time.DateTime;

import java.util.Map;

public class PersonalSheetContentBean extends SheetContentBean implements PersonalSheetContent {

    MailReceiverContent mailReceiverContent;

    public PersonalSheetContentBean(Long id, DateTime submissionDate, Map<String, SheetData> data, String uuid, MailReceiverContent mailReceiverContent) {
        super(id, submissionDate, uuid, data);
        this.mailReceiverContent = mailReceiverContent;
    }

    @Override
    public MailReceiverContent getMailReceiver() {
        return mailReceiverContent;
    }


}
