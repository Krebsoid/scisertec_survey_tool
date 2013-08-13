package de.scisertec.admin.survey.model;


import de.scisertec.admin.mailtask.model.MailReceiver;
import de.scisertec.admin.mailtask.service.MailReceiverCatalog;
import de.scisertec.admin.survey.model.view.PersonalSheetContentBean;
import de.scisertec.admin.survey.model.view.SheetContent;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class PersonalSheetBean extends SheetBean implements PersonalSheet {

    Long mailReceiverId;

    @Transient
    @Inject
    MailReceiverCatalog mailReceiverCatalog;


    public PersonalSheetBean() {
        super();
    }

    @Override
    public MailReceiver mailReceiver() {
        return mailReceiverCatalog.findById(mailReceiverId);
    }

    @Override
    public PersonalSheet mailReceiver(MailReceiver mailReceiver) {
        this.mailReceiverId = mailReceiver.id();
        return self();
    }

    @Override
    public String uuid() {
        return uuid;
    }

    @Override
    public SheetContent asContent() {
        return new PersonalSheetContentBean(id, submissionDate, data(), uuid, mailReceiver().asContent());
    }

    @Override
    public PersonalSheet self() {
        return this;
    }

}
