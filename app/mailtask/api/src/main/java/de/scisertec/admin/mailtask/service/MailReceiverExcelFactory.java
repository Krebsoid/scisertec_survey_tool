package de.scisertec.admin.mailtask.service;

import de.scisertec.admin.mailtask.model.MailReceiver;

import java.util.List;

public interface MailReceiverExcelFactory {

    List<MailReceiver> createByExcelFile(byte[] excelFile);

}
