package by.delta.specification.impl.user;

import by.delta.model.User;
import by.delta.specification.RepositoryConstParams;
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetAllUsers extends AbstractCriteriaQuerySpecification<User> {

    public GetAllUsers(Map<String, List<String>> params) {
        super(params);
    }

    protected List<Predicate> getWhereCondition(final CriteriaQuery<User> query, final Root<User> root, final CriteriaBuilder criteriaBuilder) {
        List<Predicate> conditionList = new ArrayList();
        if (!CollectionUtils.isEmpty(params)) {
            params.forEach((k, v) -> {
                if (!k.equals(RepositoryConstParams.STRING_SORT) && !k.equals(RepositoryConstParams.LIMIT_KEY) && !k.equals(RepositoryConstParams.OFFSET_KEY)) {


                    v.forEach(s -> {
                        conditionList.add(criteriaBuilder.equal(root.get(k), s));
                    });
                }

            });
        }
        return conditionList;
    }

    protected Predicate getPredicate(final CriteriaQuery<User> query, final Root<User> root, final CriteriaBuilder criteriaBuilder) {
        Predicate orClause = null;
        List<Predicate> conditionList = getWhereCondition(query, root, criteriaBuilder);
        if (!CollectionUtils.isEmpty(conditionList)) {
            if (conditionList.size() == 1) {
                orClause = conditionList.get(0);
            } else {
                orClause = criteriaBuilder.or(conditionList.toArray(new Predicate[conditionList.size()]));
            }
        }
        return orClause;
    }
}
