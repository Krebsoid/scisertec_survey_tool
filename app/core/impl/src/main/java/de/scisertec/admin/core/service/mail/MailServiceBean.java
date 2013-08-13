package de.scisertec.admin.core.service.mail;

import de.scisertec.admin.core.model.MailAttachment;
import de.scisertec.admin.core.qualifier.MailConfiguration;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MailServiceBean implements MailService {

    @Inject
    @MailConfiguration
    Configuration configuration;

    String content;
    String topic;

    String receiverName;
    String receiverMailAddress;

    String senderName;
    String senderMailAddress;

    List<MailAttachment> mailAttachments = new ArrayList<MailAttachment>();

    @Override
    public Boolean sendMail() {
        HtmlEmail htmlEmail = new HtmlEmail();

        htmlEmail.setAuthentication(configuration.getLogin(), configuration.getPassword());
        htmlEmail.setHostName(configuration.getHostName());

        try {
            htmlEmail.addTo(receiverMailAddress, receiverName);

            htmlEmail.setFrom(senderMailAddress, senderName);
            htmlEmail.setContent(null, "text/html");
            htmlEmail.setCharset("UTF8");

            htmlEmail.setSubject(topic);

            htmlEmail.setHtmlMsg(content);
            htmlEmail.send();
            return Boolean.TRUE;

        } catch (EmailException e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public MailService setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public MailService setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    @Override
    public MailService setReceiver(String receiverMailAddress) {
        this.receiverMailAddress = receiverMailAddress;
        this.receiverName = receiverMailAddress;
        return this;
    }

    @Override
    public MailService setReceiver(String receiverMailAddress, String receiverName) {
        this.receiverMailAddress = receiverMailAddress;
        this.receiverName = receiverName;
        return this;
    }

    @Override
    public MailService setSender(String senderMailAddress) {
        this.senderMailAddress = senderMailAddress;
        this.senderName = senderMailAddress;
        return this;
    }

    @Override
    public MailService setSender(String senderMailAddress, String senderName) {
        this.senderMailAddress = senderMailAddress;
        this.senderName = senderName;
        return this;
    }

    @Override
    public MailService addAttachment(MailAttachment mailAttachment) {
        this.mailAttachments.add(mailAttachment);
        return this;
    }
}
