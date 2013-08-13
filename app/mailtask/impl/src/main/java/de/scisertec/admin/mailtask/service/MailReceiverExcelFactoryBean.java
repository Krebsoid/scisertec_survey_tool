package de.scisertec.admin.mailtask.service;

import de.scisertec.admin.mailtask.model.MailReceiver;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MailReceiverExcelFactoryBean implements MailReceiverExcelFactory {

    @Inject
    MailReceiverCatalog mailReceiverCatalog;

    @Override
    public List<MailReceiver> createByExcelFile(byte[] excelFile) {
        ByteArrayInputStream stream = new ByteArrayInputStream(excelFile);
        try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(stream);

            XSSFSheet userSheet = xssfWorkbook.getSheetAt(0);
            Iterator<Row> rowIterator = userSheet.rowIterator();
            rowIterator.next();
            Integer filledRows = 0;
            while(rowIterator.hasNext()) {
                Row row = rowIterator.next();
                try {
                    int value = (int)row.getCell(0).getNumericCellValue();
                    if(value > 0) {
                        filledRows = value;
                    }
                }
                catch(Exception e) {
                    break;
                }
            }

            ArrayList<MailReceiver> receivers = new ArrayList<MailReceiver>();

            for(int i = 1 ; i < filledRows+1 ; i++) {
                XSSFRow row = userSheet.getRow(i);
                MailReceiver receiver = mailReceiverCatalog.create()
                        .emailAddress(getCellContent(row, 1))
                        .salutation(getCellContent(row, 2))
                        .title(getCellContent(row, 3))
                        .name(getCellContent(row, 5), getCellContent(row, 4))
                        .save();
                receivers.add(receiver);
            }
            return receivers;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private String getCellContent(XSSFRow row, Integer number) {
        XSSFCell cell = row.getCell(number);
        if(cell != null) {
            return StringUtils.trim(cell.getStringCellValue());
        }
        else {
            return "";
        }
    }
}
