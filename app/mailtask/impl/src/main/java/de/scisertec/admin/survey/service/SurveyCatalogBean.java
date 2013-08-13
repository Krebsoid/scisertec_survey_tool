package de.scisertec.admin.survey.service;

import de.scisertec.admin.core.exception.NoResourceFoundException;
import de.scisertec.admin.mailtask.model.MailTask;
import de.scisertec.admin.mailtask.model.view.MailTaskSummary;
import de.scisertec.admin.mailtask.qualifier.MailTaskPersistence;
import de.scisertec.admin.mailtask.service.MailTaskCatalog;
import de.scisertec.admin.mailtask.service.jpa.MailTaskQueryBuilder;
import de.scisertec.admin.survey.model.*;
import de.scisertec.admin.survey.model.scisertec.SciSerTecSurveyBean;
import de.scisertec.admin.survey.model.vcongress.vCongressSurveyBean;
import org.jboss.seam.transaction.Transactional;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class SurveyCatalogBean implements SurveyCatalog {

    @Inject
    @MailTaskPersistence
    EntityManager entityManager;

    @Inject
    MailTaskCatalog mailTaskCatalog;

    @Inject
    Instance<MailTaskQueryBuilder> queryBuilder;

    @Override
    public List<Survey> findAll() {
        return queryBuilder.get()
                .select(SurveyBean.class)
                .desc(SurveyBean_.id)
                .findAll();
    }

    @Override
    public Survey findById(Long id) {
        Survey survey = queryBuilder.get()
                .select(SurveyBean.class)
                .where()
                .equal(SurveyBean_.id, id)
                .find();

        if(survey == null) {
            throw new NoResourceFoundException("Resource not found", SurveyBean.class, id);
        }
        return survey;
    }

    @Override
    public MailTaskSurvey findByMailTask(Long id) {
        return queryBuilder.get()
                .select(MailTaskSurveyBean.class)
                .where()
                .equal(MailTaskSurveyBean_.mailTaskId, id)
                .find();
    }

    @Override
    public List<MailTaskSummary> findSuitableMailTasks(MailTaskSurvey survey) {
        List<MailTask> mailTasks = mailTaskCatalog.findAllUsable();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MailTaskSurveyBean> query = builder.createQuery(MailTaskSurveyBean.class);

        Root<MailTaskSurveyBean> root = query.from(MailTaskSurveyBean.class);
        query.where(builder.equal(root.get(MailTaskSurveyBean_.status), SurveyStatus.NOT_STARTED_YET));
        query.orderBy(builder.desc(root.get(SurveyBean_.id)));

        List<MailTaskSurveyBean> surveyBeans = entityManager.createQuery(query).getResultList();
        List<Long> usedMailTasks = new ArrayList<Long>();
        for(MailTaskSurveyBean s : surveyBeans) {
            if(!s.id().equals(survey.id()) && s.mailTaskId != null) {
                usedMailTasks.add(s.mailTaskId);
            }
        }

        ArrayList<MailTaskSummary> mailTaskSummaries = new ArrayList<MailTaskSummary>();
        for(MailTask mailTask : mailTasks) {
            Boolean available = true;
            for(Long mailTaskId : usedMailTasks) {
                if(mailTask.id().equals(mailTaskId)) {
                    available = false;
                }
            }
            if(available) {
                mailTaskSummaries.add(mailTask.asSummary());
            }
        }

        return mailTaskSummaries;
    }

    @Override
    @Transactional
    public Survey create(ConcreteSurveyType type) {

        switch (type) {
            case VCONGRESS_AFTER_CONGRESS: {
                vCongressSurveyBean surveyBean = new vCongressSurveyBean();
                surveyBean.concreteSurveyType(ConcreteSurveyType.VCONGRESS_AFTER_CONGRESS).surveyType(SurveyType.ANONYMOUS);
                entityManager.persist(surveyBean);
                entityManager.flush();
                return surveyBean;
            }
            case SCISERTEC_CUSTOMER:
                SciSerTecSurveyBean surveyBean = new SciSerTecSurveyBean();
                surveyBean.concreteSurveyType(ConcreteSurveyType.SCISERTEC_CUSTOMER).surveyType(SurveyType.ANONYMOUS);
                entityManager.persist(surveyBean);
                entityManager.flush();
                return surveyBean;
            default:
                return null;
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {
        SurveyBean surveyBean = entityManager.find(SurveyBean.class, id);
        entityManager.remove(surveyBean);
    }
}
