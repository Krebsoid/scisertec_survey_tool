package de.scisertec.admin.core.service.mail;

import de.scisertec.admin.core.model.MailAttachment;

public interface MailService {

    Boolean sendMail();

    MailService setContent(String content);
    MailService setTopic(String topic);

    MailService setReceiver(String receiverMailAddress);
    MailService setReceiver(String receiverMailAddress, String receiverName);
    MailService setSender(String senderMailAddress);
    MailService setSender(String senderMailAddress, String senderName);

    MailService addAttachment(MailAttachment mailAttachment);


}
