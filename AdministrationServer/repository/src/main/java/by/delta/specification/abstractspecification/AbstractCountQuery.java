package by.delta.specification.abstractspecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

public class AbstractCountQuery<Entity> extends AbstractCriteriaQuerySpecification implements ICountSpecification {

    public AbstractCountQuery(Map<String, List<String>> params) {
        super(params);
    }

    @Override
    public CriteriaQuery toQuery(CriteriaQuery query, Root root, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = getPredicate(query, root, criteriaBuilder);
        if (predicate != null) {
            query.where(predicate);
        }
        return query.select(criteriaBuilder.countDistinct(root));
    }

}
