package de.scisertec.admin.mailtask.service;

import de.scisertec.admin.core.exception.NoResourceFoundException;
import de.scisertec.admin.mailtask.model.MailTask;
import de.scisertec.admin.mailtask.model.MailTaskBean;
import de.scisertec.admin.mailtask.model.MailTaskBean_;
import de.scisertec.admin.mailtask.model.MailTaskStatus;
import de.scisertec.admin.mailtask.qualifier.MailTaskPersistence;
import org.jboss.seam.transaction.Transactional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MailTaskCatalogBean implements MailTaskCatalog {

    @Inject
    @MailTaskPersistence
    EntityManager entityManager;

    @Inject
    MailJobCatalog mailJobCatalog;

    @Override
    @Transactional
    public MailTask create() {
        MailTaskBean mailTaskBean = new MailTaskBean();
        entityManager.persist(mailTaskBean);
        entityManager.flush();
        return mailTaskBean;
    }

    @Override
    public MailTask findById(Long id) {
        if(id == null) {
            return null;
        }
        MailTask result;
        result = entityManager.find(MailTaskBean.class, id);
        if(result == null) {
            throw new NoResourceFoundException("Resource not found", MailTaskBean.class, id);
        }
        return result;
    }

    @Override
    public List<MailTask> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MailTaskBean> query = builder.createQuery(MailTaskBean.class);

        Root<MailTaskBean> root = query.from(MailTaskBean.class);
        query.orderBy(builder.desc(root.get(MailTaskBean_.id)));

        List<MailTaskBean> mailTaskBeans = entityManager.createQuery(query).getResultList();

        return new ArrayList<MailTask>(mailTaskBeans);
    }

    @Override
    public List<MailTask> findAllUsable() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MailTaskBean> query = builder.createQuery(MailTaskBean.class);

        Root<MailTaskBean> root = query.from(MailTaskBean.class);
        Predicate predicate = builder.and(builder.equal(root.get(MailTaskBean_.status), MailTaskStatus.NOT_STARTED_YET),
                builder.isNotNull(root.get(MailTaskBean_.comment)),
                builder.isNotEmpty(root.get(MailTaskBean_.mailJobs)),
                builder.isTrue(root.get(MailTaskBean_.contentAvailable)));
        query.where(predicate);
        query.orderBy(builder.desc(root.get(MailTaskBean_.id)));

        List<MailTaskBean> mailTaskBeans = entityManager.createQuery(query).getResultList();

        return new ArrayList<MailTask>(mailTaskBeans);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        MailTaskBean mailTaskBean = entityManager.find(MailTaskBean.class, id);
        entityManager.remove(mailTaskBean);
    }

}
