package de.scisertec.admin.survey.service;

import de.scisertec.admin.core.exception.NoResourceFoundException;
import de.scisertec.admin.mailtask.qualifier.MailTaskPersistence;
import de.scisertec.admin.mailtask.service.MailReceiverCatalog;
import de.scisertec.admin.mailtask.service.jpa.MailTaskQueryBuilder;
import de.scisertec.admin.survey.model.*;
import de.scisertec.admin.survey.model.vcongress.vCongressSheet;
import de.scisertec.admin.survey.model.vcongress.vCongressSheetBean;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.EntityManager;

public class SheetCatalogBean implements SheetCatalog {

    @Inject
    @MailTaskPersistence
    EntityManager entityManager;

    @Inject
    MailReceiverCatalog mailReceiverCatalog;

    @Inject
    Instance<MailTaskQueryBuilder> queryBuilder;


    @Override
    public Sheet create(ConcreteSurveyType surveyType) {
        switch (surveyType) {
            case VCONGRESS_AFTER_CONGRESS: {
                vCongressSheet sheetBean = new vCongressSheetBean();
                entityManager.persist(sheetBean);
                entityManager.flush();
                return sheetBean;
            }
            case SCISERTEC_CUSTOMER:
                Sheet sheetBean = new SheetBean();
                entityManager.persist(sheetBean);
                entityManager.flush();
                return sheetBean;
            default:
                return null;
        }
    }

    @Override
    public Sheet findByUuid(String uuid) {
        SheetBean sheet = queryBuilder.get().select(SheetBean.class)
                .where()
                .equal(SheetBean_.uuid, uuid)
                .find();

        if(sheet == null) {
            throw new NoResourceFoundException("Resource not found", SheetBean.class, uuid);
        }

        return sheet;
    }
}
