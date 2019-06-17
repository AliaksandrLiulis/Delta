package by.delta.specification.impl.incoming.countSpecification;

import by.delta.model.Incoming;
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

public class GetCountOfIncomingMessage extends AbstractCountQuery<Incoming> {

    public GetCountOfIncomingMessage(Map<String, List<String>> params) {
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
        }
        return conditionList;
    }
}