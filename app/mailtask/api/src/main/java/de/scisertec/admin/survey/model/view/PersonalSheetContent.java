package de.scisertec.admin.survey.model.view;

import de.scisertec.admin.mailtask.model.view.MailReceiverContent;

public interface PersonalSheetContent extends SheetContent {

    MailReceiverContent getMailReceiver();

}
