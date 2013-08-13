package de.scisertec.admin.mailtask.service;

import de.scisertec.admin.mailtask.exception.InvalidTemplateException;
import de.scisertec.admin.mailtask.model.view.MailReceiverContent;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.core.document.SyntaxKind;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MailTaskTemplateFactoryBean implements MailTaskTemplateFactory {

    @Override
    public String processTemplate(byte[] content, MailReceiverContent mailReceiver) {
        ByteArrayInputStream stream = new ByteArrayInputStream(content);
        try {
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(stream, TemplateEngineKind.Freemarker);
            IOUtils.closeQuietly(stream);

            IContext reportContext = report.createContext();

            FieldsMetadata metadata = report.createFieldsMetadata();
            metadata.addFieldAsTextStyling("mail", SyntaxKind.Html);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Options options = Options.getTo(ConverterTypeTo.XHTML).via(ConverterTypeVia.ODFDOM);

            if(mailReceiver != null) {
                reportContext.put("mail", mailReceiver);
            }

            report.convert(reportContext, options, outputStream);

            return outputStream.toString("UTF8");

        } catch (IOException e) {
            throw new InvalidTemplateException();
        } catch (XDocReportException e) {
            throw new InvalidTemplateException();
        }
    }

}
