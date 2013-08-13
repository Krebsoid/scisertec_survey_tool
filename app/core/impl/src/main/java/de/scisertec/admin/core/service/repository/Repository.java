package de.scisertec.admin.core.service.repository;


import org.jboss.seam.transaction.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public class Repository<E> {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void persist(E entity) {
        entityManager.persist(entity);
    }

    @SuppressWarnings("unchecked")
    public E findById(Class clazz, Long id) {
        return (E) entityManager.find(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public E find(CriteriaQuery query) {
        E result = null;
        try {
            result = (E) entityManager.createQuery(query).getSingleResult();
            return result;
        } catch (NoResultException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<E> findAll(Class clazz) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = criteriaBuilder.createQuery(clazz);
        query.from(clazz);
        return (List<E>) entityManager.createQuery(query).getResultList();
    }
    @SuppressWarnings("unchecked")
    public List<E> findAll(CriteriaQuery query) {
        return (List<E>) entityManager.createQuery(query).getResultList();
    }
    @SuppressWarnings("unchecked")
    public List<E> findAll(CriteriaQuery query, Integer amount) {
        return (List<E>) entityManager.createQuery(query).setMaxResults(amount).getResultList();
    }

    public CriteriaBuilder getBuilder() {
        return entityManager.getCriteriaBuilder();
    }

}
