package de.scisertec.admin.mailtask.service;

import de.scisertec.admin.mailtask.model.MailTask;

public interface MailTaskDelivery {

    void test(MailTask mailTask, String mailAddress);
    String testMailContent(MailTask mailTask);

    void start(MailTask mailTask);

}
