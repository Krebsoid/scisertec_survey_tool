package de.scisertec.admin.core.service.jpa;


import javax.persistence.EntityManager;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

public interface QueryBuilder {

    QueryBuilder select(Class type);
    QueryBuilder join(SingularAttribute attribute);
    QueryBuilder join(ListAttribute attribute);
    QueryBuilder join(SetAttribute attribute);

    QueryBuilder where();

    QueryBuilder equal(SingularAttribute attribute, Object compareValue);
    QueryBuilder equal(SingularAttribute attribute, SingularAttribute compareValue);
    QueryBuilder isNotNull(SingularAttribute attribute);
    QueryBuilder isEmpty(PluralAttribute attribute);
    QueryBuilder isFalse(SingularAttribute attribute);

    QueryBuilder or(SingularAttribute attribute, Object compareValue);

    QueryBuilder asc(SingularAttribute attribute);
    QueryBuilder desc(SingularAttribute attribute);

    <T> T find();
    <T> List<T> findAll();
    <T> List<T> findAll(PageConstraint pageConstraint);

    EntityManager getEntityManager();

}
