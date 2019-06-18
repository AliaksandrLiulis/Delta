package by.delta.specification.impl.message.countSpecification;

import by.delta.model.Message;
import by.delta.specification.RepositoryConstParams;
import by.delta.specification.abstractspecification.AbstractCountQuery;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetCountOfMessage extends AbstractCountQuery<Message> {

    public GetCountOfMessage(Map<String, List<String>> params) {
        super(params);
    }

    @Override
    protected List<Predicate> getWhereCondition(CriteriaQuery query, Root root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> conditionList = new ArrayList();
        if (!CollectionUtils.isEmpty(params)) {
            params.forEach((o, o2) -> {
                if (o.toString().equalsIgnoreCase(RepositoryConstParams.ID_KEY)) {
                    List<String> list = (List<String>) o2;
                    ((List<String>) o2).forEach(s -> {
                        conditionList.add(criteriaBuilder.equal(root.get("face").get(o.toString()), s));

                    });
                }
            });
            conditionList.add(criteriaBuilder.equal(root.get("removed"), 0));
        }
        return conditionList;
    }

    protected Predicate getPredicate(final CriteriaQuery query, final Root root, final CriteriaBuilder criteriaBuilder) {
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