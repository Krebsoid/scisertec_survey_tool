package de.scisertec.admin.survey.resource.vcongress;

import de.scisertec.admin.survey.model.*;
import de.scisertec.admin.survey.model.vcongress.vCongressSheet;
import de.scisertec.admin.survey.model.vcongress.vCongressSurvey;
import de.scisertec.admin.survey.model.vcongress.view.vCongressSheetContent;
import de.scisertec.admin.survey.model.view.SheetContent;
import de.scisertec.admin.survey.service.SheetCatalog;
import de.scisertec.admin.survey.service.SurveyCatalog;
import org.jboss.solder.logging.Logger;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.ws.rs.PathParam;

public class vCongressSheetResourceBean implements vCongressSheetResource {

    @Inject
    SheetCatalog sheetCatalog;

    @Inject
    SurveyCatalog surveyCatalog;

    @Inject
    Logger logger;

    @Override
    public SheetContent getContent(@PathParam("sheetUuid") String sheetUuid) {
        Sheet sheet = sheetCatalog.findByUuid(sheetUuid);
        return sheet.asContent();
    }

    @Override
    public SheetContent fill(@PathParam("sheetUuid") String sheetUuid, SheetFillRequest request) {
        Sheet sheet = sheetCatalog.findByUuid(sheetUuid);
        sheet.data(request.getKey(), request.getValue()).save();
        logger.info("Person filled " + request.getKey() + " with " + request.getValue());
        return sheet.asContent();
    }

    @Override
    public SheetContent create(@PathParam("surveyId") Long surveyId) {
        vCongressSurvey survey = (vCongressSurvey) surveyCatalog.findById(surveyId);
        vCongressSheet sheet = (vCongressSheet) sheetCatalog.create(ConcreteSurveyType.VCONGRESS_AFTER_CONGRESS);
        sheet.payment(survey.payment()).abstractSubmission(survey.abstractSubmission()).reviewing(survey.reviewing()).save();

        survey.sheet(sheet).save();
        logger.info("New participant entered survey");
        logger.info("------------------------------------------------");
        return sheet.asContent();
    }

    @Override
    public SheetContent submit(@PathParam("sheetUuid") String sheetUuid) {
        Sheet sheet = sheetCatalog.findByUuid(sheetUuid);
        sheet.submissionDate(new DateTime()).save();
        return sheet.asContent();
    }
}
