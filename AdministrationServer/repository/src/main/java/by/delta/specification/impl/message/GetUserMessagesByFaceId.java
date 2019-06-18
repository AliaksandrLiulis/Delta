package by.delta.specification.impl.message;

import by.delta.model.Message;
import by.delta.specification.RepositoryConstParams;
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetUserMessagesByFaceId extends AbstractCriteriaQuerySpecification<Message> {

    public GetUserMessagesByFaceId(Map<String, List<String>> params) {
        super(params);
    }

    @Override
    public List<Predicate> getWhereCondition(CriteriaQuery<Message> query, Root<Message> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> conditionList = new ArrayList();
        if (!CollectionUtils.isEmpty(params)) {
                params.forEach((k, v) -> {
                    if (k.equalsIgnoreCase(RepositoryConstParams.ID_KEY)) {
                        v.forEach(s -> {
                            conditionList.add(criteriaBuilder.equal(root.get("face").get(k), s));

                        });
                    }
                });
            conditionList.add(criteriaBuilder.equal(root.get("removed"), 0));
        }
        return conditionList;
    }

    protected Predicate getPredicate(final CriteriaQuery<Message> query, final Root<Message> root, final CriteriaBuilder criteriaBuilder) {
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
