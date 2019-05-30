package by.delta.specification.impl.user;

import by.delta.model.User;
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

public class GetUserByEmail extends AbstractCriteriaQuerySpecification<User> {

    public GetUserByEmail(Map<String, List<String>> params) {
        super(params);

    }

    @Override
    public List<Predicate> getWhereCondition(CriteriaQuery<User> query, Root<User> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> conditionList = new ArrayList();
        if (!CollectionUtils.isEmpty(params)) {
            params.forEach((k, v) -> {
                if (k.equalsIgnoreCase(RepositoryConstParams.EMAIL_KEY)) {
                    v.forEach(s -> {
                        conditionList.add(criteriaBuilder.equal(root.get(k), s));
                    });
                }
            });
        }
        return conditionList;
    }
}