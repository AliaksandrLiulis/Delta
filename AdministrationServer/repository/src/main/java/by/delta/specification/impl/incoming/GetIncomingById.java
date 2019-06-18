package by.delta.specification.impl.incoming;

import by.delta.model.Face;
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

public class GetIncomingById extends AbstractCriteriaQuerySpecification<Incoming> {

    public GetIncomingById(Map<String, List<String>> params) {
        super(params);
    }

    @Override
    public List<Predicate> getWhereCondition(CriteriaQuery<Incoming> query, Root<Incoming> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> conditionList = new ArrayList();
        if (!CollectionUtils.isEmpty(params)) {
            params.forEach((k, v) -> {
                if (k.equalsIgnoreCase(RepositoryConstParams.ID_KEY)) {
                    v.forEach(s -> {
                        conditionList.add(criteriaBuilder.equal(root.join("message").get(k), s));
                    });
                }
            });
        }
        return conditionList;
    }
}
