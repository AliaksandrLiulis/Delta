package by.delta.specification.abstractspecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public interface ICountSpecification {

    CriteriaQuery toQuery(CriteriaQuery query, Root root, CriteriaBuilder criteriaBuilder);
}
