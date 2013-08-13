package de.scisertec.admin.mailtask.resource;

import de.scisertec.admin.mailtask.model.*;
import de.scisertec.admin.mailtask.model.view.MailJobEntry;
import de.scisertec.admin.mailtask.service.MailJobCatalog;
import de.scisertec.admin.mailtask.service.MailReceiverCatalog;
import de.scisertec.admin.mailtask.service.MailReceiverExcelFactory;
import de.scisertec.admin.mailtask.service.MailTaskCatalog;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MailJobResourceBean implements MailJobResource {

    @Inject
    MailTaskCatalog mailTaskCatalog;

    @Inject
    MailReceiverCatalog mailReceiverCatalog;

    @Inject
    MailJobCatalog mailJobCatalog;

    @Inject
    MailReceiverExcelFactory mailReceiverFactory;

    public List<MailJobEntry> getMailJobs(@PathParam("id") Long mailTaskId) {
        MailTask mailTask = mailTaskCatalog.findById(mailTaskId);
        List<MailJobEntry> mailJobs = new ArrayList<MailJobEntry>();
        for(MailJob mj : mailTask.mailJobs()) {
            mailJobs.add(mj.asEntry());
        }
        return mailJobs;
    }

    public void addReceiverList(@PathParam("id") Long mailTaskId, @MultipartForm MailReceiverImportRequest request) {
        MailTask mailTask = mailTaskCatalog.findById(mailTaskId);
        List<MailReceiver> mailReceiver = mailReceiverFactory.createByExcelFile(request.getImportFile());
        mailTask.receiver(mailReceiver).save();
    }

    public void addReceiver(@PathParam("id") Long mailTaskId, MailReceiverCreationRequest request) {
        MailTask mailTask = mailTaskCatalog.findById(mailTaskId);
        MailReceiver mailReceiver = mailReceiverCatalog.create(request);
        mailTask.receiver(mailReceiver).save();
    }

    public void deleteAll(@PathParam("id") Long mailTaskId) {
        MailTask mailTask = mailTaskCatalog.findById(mailTaskId);
        mailTask.removeAllMailJobs().save();
    }

    public void delete(@PathParam("id") Long mailTaskId, @PathParam("mailJobId") Long mailJobId) {
        MailTask mailTask = mailTaskCatalog.findById(mailTaskId);
        MailJob mailJob = mailJobCatalog.findById(mailJobId);
        mailTask.removeMailJob(mailJob).save();
    }

}
