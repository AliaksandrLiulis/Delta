package by.delta.specification.impl.incoming;

import by.delta.model.Incoming;
import by.delta.specification.RepositoryConstParams;
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetIncomingByIdFace extends AbstractCriteriaQuerySpecification<Incoming> {

    public GetIncomingByIdFace(Map<String, List<String>> params) {
        super(params);
    }

    @Override
    public List<Predicate> getWhereCondition(CriteriaQuery<Incoming> query, Root<Incoming> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> conditionList = new ArrayList();
        if (!CollectionUtils.isEmpty(params)) {
            params.forEach((k, v) -> {
                if (k.equalsIgnoreCase(RepositoryConstParams.ID_KEY)) {
                    v.forEach(s -> {
                        conditionList.add(criteriaBuilder.equal(root.join("face").get(k), s));
                        conditionList.add(criteriaBuilder.equal(root.get("messageState"), 1));
                    });
                }

            });
        }
        return conditionList;
    }

    @Override
    protected Predicate getPredicate(final CriteriaQuery<Incoming> query, final Root<Incoming> root, final CriteriaBuilder criteriaBuilder) {
        Predicate orClause = null;
        List<Predicate> conditionList = getWhereCondition(query, root, criteriaBuilder);
        if (!CollectionUtils.isEmpty(conditionList)) {
            if (conditionList.size() == 1) {
                orClause = conditionList.get(0);
            } else {
                orClause = criteriaBuilder.and(conditionList.toArray(new Predicate[conditionList.size()]));
            }
        }
        return orClause;
    }
}
