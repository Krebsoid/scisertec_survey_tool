package de.scisertec.admin.survey.resource.scisertec;

import de.scisertec.admin.person.service.PersonCatalog;
import de.scisertec.admin.survey.model.*;
import de.scisertec.admin.survey.model.scisertec.SciSerTecSurvey;
import de.scisertec.admin.survey.model.view.SheetContent;
import de.scisertec.admin.survey.service.SheetCatalog;
import de.scisertec.admin.survey.service.SurveyCatalog;
import org.jboss.solder.logging.Logger;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

public class SciSerTecSheetResourceBean implements SciSerTecSheetResource {

    @Inject
    SheetCatalog sheetCatalog;

    @Inject
    SurveyCatalog surveyCatalog;

    @Inject
    PersonCatalog personCatalog;

    @Inject
    Logger logger;

    @Override
    public SheetContent getContent(@PathParam("sheetUuid") String sheetUuid) {
        Sheet sheet = sheetCatalog.findByUuid(sheetUuid);
        return sheet.asContent();
    }

    @Override
    public SheetContent fill(@PathParam("sheetUuid") String sheetUuid, SheetFillRequest request, @Context HttpServletRequest req) {
        Sheet sheet = sheetCatalog.findByUuid(sheetUuid);
        sheet.data(request.getKey(), request.getValue()).data("ip",req.getRemoteHost()).save();
        logger.info(req.getRemoteAddr() + " Person filled " + request.getKey() + " with " + request.getValue());
        return sheet.asContent();
    }

    @Override
    public SheetContent create(@PathParam("surveyId") Long surveyId, @Context HttpServletRequest req) {
        Sheet sheet = sheetCatalog.create(ConcreteSurveyType.SCISERTEC_CUSTOMER);
        Survey survey = surveyCatalog.findById(surveyId);
        survey.sheet(sheet).save();
        logger.info(req.getRemoteAddr() + " New person entered survey");
        logger.info("------------------------------------------------");
        return sheet.asContent();
    }

    @Override
    public SheetContent submit(@PathParam("surveyId") Long surveyId, @PathParam("sheetUuid") String sheetUuid, PersonCreateRequest person, @Context HttpServletRequest req) {
        Sheet sheet = sheetCatalog.findByUuid(sheetUuid);
        SciSerTecSurvey survey = (SciSerTecSurvey) surveyCatalog.findById(surveyId);
        sheet.submissionDate(new DateTime()).save();
        logger.info("------------------------------------------------------");
        if(person.isToSave()) {
            personCatalog.create(person).user().addRelationship(survey.group()).save();
        }
        logger.info(req.getRemoteAddr() + " Person submitted survey");
        return sheet.asContent();
    }
}
