package de.scisertec.admin.mailtask.service;

import de.scisertec.admin.mailtask.model.MailTask;

import java.util.List;

public interface MailTaskCatalog {

    MailTask findById(Long id);
    List<MailTask> findAll();

    List<MailTask> findAllUsable();

    MailTask create();

    void delete(Long id);

}
