package de.scisertec.admin.survey.model;

import de.scisertec.admin.mailtask.model.MailReceiver;

public interface PersonalSheet extends Sheet {

    MailReceiver mailReceiver();

    PersonalSheet mailReceiver(MailReceiver mailReceiver);

}
