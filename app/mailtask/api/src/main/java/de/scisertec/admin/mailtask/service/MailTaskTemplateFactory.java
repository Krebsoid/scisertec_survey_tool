package de.scisertec.admin.mailtask.service;

import de.scisertec.admin.mailtask.model.view.MailReceiverContent;

public interface MailTaskTemplateFactory {

    String processTemplate(byte[] content, MailReceiverContent receiver);

}
