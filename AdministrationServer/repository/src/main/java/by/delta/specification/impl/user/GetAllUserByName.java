package by.delta.specification.impl.user;

import by.delta.model.User;
import by.delta.specification.RepositoryConstParams;
import by.delta.specification.abstractspecification.AbstractCriteriaQuerySpecification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class GetAllUserByName extends AbstractCriteriaQuerySpecification<User> {

    public GetAllUserByName(Map<String, List<String>> params) {
        super(params);
    }

    @Override
    public List<Predicate> getWhereCondition(CriteriaQuery<User> query, Root<User> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> conditionList = new ArrayList();
        if (!CollectionUtils.isEmpty(params)) {
            params.forEach((k, v) -> {
                if (k.equalsIgnoreCase(RepositoryConstParams.NAME_KEY)) {
                    v.forEach(s -> {
                        conditionList.add(criteriaBuilder.equal(root.get(k), s));
                    });
                }
            });
        }
        return conditionList;
    }
}