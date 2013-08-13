package de.scisertec.admin.mailtask.resource;


import de.scisertec.admin.mailtask.model.MailTask;
import de.scisertec.admin.mailtask.model.MailTaskTestRequest;
import de.scisertec.admin.mailtask.model.MailTaskUpdateRequest;
import de.scisertec.admin.mailtask.model.MailTemplateImportRequest;
import de.scisertec.admin.mailtask.model.view.MailTaskSummary;
import de.scisertec.admin.mailtask.model.view.MailTaskDetailed;
import de.scisertec.admin.mailtask.service.MailTaskCatalog;
import de.scisertec.admin.mailtask.service.MailTaskDelivery;
import de.scisertec.admin.mailtask.service.MailTaskTemplateFactory;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class MailTaskResourceBean implements MailTaskResource {

    @Inject
    MailTaskCatalog mailTaskCatalog;

    @Inject
    MailTaskTemplateFactory mailTaskTemplateFactory;

    @Inject
    MailTaskDelivery mailTaskDelivery;


    @Override
    public List<MailTaskSummary> readAll() {
        List<MailTaskSummary> mailTasks = new ArrayList<MailTaskSummary>();
        for(MailTask mailTask : mailTaskCatalog.findAll()) {
            mailTasks.add(mailTask.asSummary());
        }
        return mailTasks;
    }

    @Override
    public MailTaskDetailed read(@PathParam("id") Long mailTaskId) {
        MailTask mailTask = mailTaskCatalog.findById(mailTaskId);
        return mailTask.asDetailed();
    }

    @Override
    public MailTaskDetailed update(@PathParam("id") Long mailTaskId, MailTaskUpdateRequest mailTaskUpdateRequestBean) {
        MailTask mailTask = mailTaskCatalog.findById(mailTaskId);
        MailTask updatedMailTask = mailTask.update(mailTaskUpdateRequestBean).save();
        return updatedMailTask.asDetailed();
    }

    @Override
    public void updateTemplate(@PathParam("id") Long mailTaskId, @MultipartForm MailTemplateImportRequest request) {
        MailTask mailTask = mailTaskCatalog.findById(mailTaskId);
        mailTask.template(request.getImportFile()).save();
    }

    @Override
    public String readTemplate(@PathParam("id") Long mailTaskId) {
        MailTask mailTask = mailTaskCatalog.findById(mailTaskId);
        return mailTaskDelivery.testMailContent(mailTask);
    }

    @Override
    public void send(@PathParam("id") Long mailTaskId) {
        MailTask mailTask = mailTaskCatalog.findById(mailTaskId);
        mailTaskDelivery.start(mailTask);
    }

    @Override
    public void testTemplate(@PathParam("id") Long mailTaskId, MailTaskTestRequest request) {
        MailTask mailTask = mailTaskCatalog.findById(mailTaskId);
        mailTaskDelivery.test(mailTask, request.getEmailAddress());
    }

    @Override
    public MailTaskDetailed create() {
        MailTask mailTask = mailTaskCatalog.create();
        return mailTask.asDetailed();
    }

    public void delete(@PathParam("id") Long mailTaskId) {
        mailTaskCatalog.delete(mailTaskId);
    }

}
