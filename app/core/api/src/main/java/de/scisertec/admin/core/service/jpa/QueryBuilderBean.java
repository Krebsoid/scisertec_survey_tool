package de.scisertec.admin.core.service.jpa;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class QueryBuilderBean implements QueryBuilder {

    CriteriaQuery query;
    CriteriaBuilder builder;
    Map<Class, From> rootMap = new HashMap<Class, From>();
    List<Predicate> predicates = new ArrayList<Predicate>();

    List<Predicate> tempPredicates = new ArrayList<Predicate>();

    Root root;

    public QueryBuilder select(Class type) {
        builder = getEntityManager().getCriteriaBuilder();
        query = builder.createQuery(type);
        root = query.from(type);
        rootMap.put(type, root);
        return this;
    }

    public QueryBuilder join(SingularAttribute attribute) {
        Join join = root.join(attribute);
        rootMap.put(attribute.getType().getJavaType(), join);
        return this;
    }

    public QueryBuilder join(ListAttribute attribute) {
        Join join = root.join(attribute);
        rootMap.put(attribute.getElementType().getJavaType(), join);
        return this;
    }

    public QueryBuilder join(SetAttribute attribute) {
        Join join = root.join(attribute);
        rootMap.put(attribute.getElementType().getJavaType(), join);
        return this;
    }

    public QueryBuilder where() {
        return this;
    }

    public QueryBuilder equal(SingularAttribute attribute, Object compareValue) {
        From from = rootMap.get(attribute.getJavaMember().getDeclaringClass());
        Predicate predicate = builder.equal(from.get(attribute), compareValue);
        tempPredicates.add(predicate);
        return this;
    }

    public QueryBuilder equal(SingularAttribute attribute, SingularAttribute compareValue) {
        From from = rootMap.get(attribute.getJavaMember().getDeclaringClass());
        From to = rootMap.get(compareValue.getJavaMember().getDeclaringClass());
        Predicate predicate = builder.equal(from.get(attribute), to.get(compareValue));
        tempPredicates.add(predicate);
        return this;
    }

    public QueryBuilder isNotNull(SingularAttribute attribute) {
        From from = rootMap.get(attribute.getJavaMember().getDeclaringClass());
        Predicate predicate = builder.isNotNull(from.get(attribute));
        tempPredicates.add(predicate);
        return this;
    }

    public QueryBuilder isEmpty(PluralAttribute attribute) {
        From from = rootMap.get(attribute.getJavaMember().getDeclaringClass());
        Predicate predicate = builder.isEmpty(from.get(attribute));
        tempPredicates.add(predicate);
        return this;
    }

    public QueryBuilder isFalse(SingularAttribute attribute) {
        From from = rootMap.get(attribute.getJavaMember().getDeclaringClass());
        Predicate predicate = builder.isFalse(from.get(attribute));
        tempPredicates.add(predicate);
        return this;
    }

    public QueryBuilder or(SingularAttribute attribute, Object compareValue) {
        processTempPredicates();
        tempPredicates.clear();
        return this;
    }

    public QueryBuilder asc(SingularAttribute attribute) {
        From from = rootMap.get(attribute.getJavaMember().getDeclaringClass());
        query.orderBy(builder.asc(from.get(attribute)));
        return this;
    }

    public QueryBuilder desc(SingularAttribute attribute) {
        From from = rootMap.get(attribute.getJavaMember().getDeclaringClass());
        query.orderBy(builder.desc(from.get(attribute)));
        return this;
    }

    public <T> T find() {
        try {
            processTempPredicates();
            prepareFinalPredicate();
            return (T) getEntityManager().createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            return null;
        }
    }

    public <T> List<T> findAll() {
        try {
            processTempPredicates();
            prepareFinalPredicate();
            return getEntityManager().createQuery(query).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public <T> List<T> findAll(PageConstraint pageConstraint) {
        try {
            processTempPredicates();
            prepareFinalPredicate();
            return getEntityManager().createQuery(query)
                    .setFirstResult(pageConstraint.getFirstResult())
                    .setMaxResults(pageConstraint.getMaxResults())
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }


    public abstract EntityManager getEntityManager();



    private void processTempPredicates() {
        if(tempPredicates.size() > 1) {
            Predicate tempPredicate = builder.and(tempPredicates.toArray(new Predicate[tempPredicates.size()]));
            predicates.add(tempPredicate);
        }
        else if(!tempPredicates.isEmpty()) {
            predicates.add(tempPredicates.get(0));
        }
    }

    private void prepareFinalPredicate() {
        Predicate finalPredicate;
        if(predicates.size() > 1) {
            finalPredicate = builder.or(predicates.toArray(new Predicate[tempPredicates.size()]));
            query.where(finalPredicate);
        }
        else if(!tempPredicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[predicates.size()]));
        }
    }
}
