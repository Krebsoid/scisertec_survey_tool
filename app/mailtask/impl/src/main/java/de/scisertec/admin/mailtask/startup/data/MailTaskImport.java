package de.scisertec.admin.mailtask.startup.data;

import de.scisertec.admin.mailtask.model.MailReceiver;
import de.scisertec.admin.mailtask.model.MailReceiverBean;
import de.scisertec.admin.mailtask.model.MailTask;
import de.scisertec.admin.mailtask.model.MailTaskBean;
import de.scisertec.admin.mailtask.qualifier.MailTaskPersistence;
import de.scisertec.admin.mailtask.service.MailReceiverCatalog;
import de.scisertec.admin.mailtask.service.MailTaskCatalog;
import de.scisertec.admin.person.model.Person;
import org.jboss.seam.transaction.Transactional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;

@ApplicationScoped
public class MailTaskImport {



    @Inject
    MailTaskCatalog mailTaskCatalog;

    @Inject
    MailReceiverCatalog mailReceiverCatalog;



    MailTask mailTask1;
    MailTask mailTask2;

    MailReceiver receiver1;
    MailReceiver receiver2;

    MailReceiver receiver3;
    MailReceiver receiver4;
    MailReceiver receiver5;
    MailReceiver receiver6;

    @Transactional
    public void initialize() {

        mailTask1 = mailTaskCatalog.create();
        mailTask2 = mailTaskCatalog.create();

        receiver1 = mailReceiverCatalog.create()
                .emailAddress("tester1@team-neotech.de")
                .salutation("Dear")
                .name(" Bruweleit", " Malte")
                .save();

        receiver2 = mailReceiverCatalog.create()
                .emailAddress("tester2@team-neotech.de")
                .salutation("Dear")
                .name(" Happel"," Ernst")
                .save();

        receiver3 = mailReceiverCatalog.create()
                .emailAddress("tester3@team-neotech.de")
                .salutation("Dear")
                .name(" Bruweleit", " Horst")
                .save();

        receiver4 = mailReceiverCatalog.create()
                .emailAddress("tester4@team-neotech.de")
                .salutation("Dear")
                .name(" Bruweleit", " Melanie")
                .save();

        receiver5 = mailReceiverCatalog.create()
                .emailAddress("tester5@team-neotech.de")
                .salutation("Dear")
                .name(" Bruweleit", " Till")
                .save();

        receiver6 = mailReceiverCatalog.create()
                .emailAddress("tester6@team-neotech.de")
                .salutation("Dear")
                .name(" Bruweleit", " Uschi")
                .save();

        ArrayList<MailReceiver> mailReceiver = new ArrayList<MailReceiver>();
        mailReceiver.add(receiver1);
        mailReceiver.add(receiver2);


        mailTask1.comment("Comment").topic("Topic")
                .receiver(mailReceiver).save();

        ArrayList<MailReceiver> mailReceiver2 = new ArrayList<MailReceiver>();
        mailReceiver2.add(receiver3);
        mailReceiver2.add(receiver4);
        mailReceiver2.add(receiver5);
        mailReceiver2.add(receiver6);


        mailTask2.comment("Comment2").topic("Topic2")
                .receiver(mailReceiver2).save();


    }

}
